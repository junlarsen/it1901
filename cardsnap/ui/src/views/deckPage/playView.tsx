import { FC, useEffect, useState } from 'react';
import { PlayCardCard } from '../deckPage/playCardCard';
import { PlayButtons } from '../deckPage/playButtons';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card } from '../../helpers/mockData';
import { CardsNotFound } from '../../components/cardsNotFound';

interface PlayViewProps {
  id: string;
}

export const PlayView: FC<PlayViewProps> = ({ id }) => {
  const [displayAnswer, setDisplayAnswer] = useState(false);
  const [deckIndex, setDeckIndex] = useState(0);

  const { isLoading, data, isSuccess, isError } = useQuery(['cards', id], () =>
    axios.get(DECKS_ENDPOINTS + id + '/cards/').then((res: AxiosResponse<Card[]>) => res.data),
  );

  return (
    <div className="ml-auto mr-auto w-full min-w-200 mt-8">
      {isLoading && <p>Loading...</p>}
      {isSuccess && data.length > 0 && (
        <>
          <PlayCardCard
            card={data[deckIndex]}
            currentCount={deckIndex + 1}
            totalCount={data.length}
            displayAnswer={displayAnswer}
          />
          <PlayButtons
            displayAnswer={displayAnswer}
            setDisplayAnswer={setDisplayAnswer}
            setDeckIndex={setDeckIndex}
            deckSize={data.length}
            deckIndex={deckIndex}
          />
        </>
      )}
      {isSuccess && data.length < 1 && <p>This deck is empty, press edit to add cards</p>}
      {isError && <CardsNotFound id={id} />}
    </div>
  );
};
