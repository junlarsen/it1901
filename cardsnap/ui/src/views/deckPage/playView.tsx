import { FC, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CardCard } from './cardCard/CardCard';
import { IndexIndicator } from './indexIndicator';
import { PlayButtons } from '../deckPage/playButtons';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card } from '../../helpers/mockData';
import { CardsNotFound } from '../../components/cardsNotFound/cardsNotFound';

interface PlayViewProps {
  id: string;
}

/**
 * Gets all cards in deck from REST API and displays one at time in playview.
 * @param id string deck id
 */
export const PlayView: FC<PlayViewProps> = ({ id }) => {
  const [displayAnswer, setDisplayAnswer] = useState(false);
  const [cardIndex, setCardIndex] = useState(0);

  /**
   * Gets cards from a deck with a given ID as a list with API call.
   * GET(localhost:xxxx/api/decks/{deckid}/)
   * If sucess the data is a list of cards
   */
  const { isLoading, data, isSuccess, isError } = useQuery(['cards', id], () =>
    axios.get(DECKS_ENDPOINTS + id + '/cards/').then((res: AxiosResponse<Card[]>) => res.data),
  );

  /**
   * Renders the playview if the API Call is sucessfull.
   * Renders errormessage if something went wrong during the API Call.
   */
  return (
    <div className="w-full m-auto">
      {isLoading && <p>Loading...</p>}
      {isSuccess && data.length > 0 && (
        <div>
          <IndexIndicator currentCount={cardIndex + 1} totalCount={data.length} />
          <CardCard card={data[cardIndex]} displayAnswer={displayAnswer} />
          <PlayButtons
            displayAnswer={displayAnswer}
            setDisplayAnswer={setDisplayAnswer}
            setCardIndex={setCardIndex}
            deckSize={data.length}
            cardIndex={cardIndex}
          />
        </div>
      )}
      {isSuccess && data.length < 1 && <p>This deck is empty, press edit to add cards</p>}
      {isError && <CardsNotFound id={id} />}
    </div>
  );
};
