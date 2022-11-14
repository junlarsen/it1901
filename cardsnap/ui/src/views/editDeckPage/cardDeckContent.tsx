import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CreateCardContainer } from './createCardContainer';
import { CardContainer } from './cardContainer';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card, CardDeck } from '../../helpers/mockData';
import { CardsNotFound } from '../../components/cardsNotFound';

interface CardDeckContentProps {
  deck: CardDeck;
}

export const CardDeckContent: FC<CardDeckContentProps> = ({ deck }) => {
  const { isLoading, data, isSuccess, isError, refetch } = useQuery(['cards', deck.id], () =>
    axios.get(`${DECKS_ENDPOINTS}${deck.id}/cards/`).then((res: AxiosResponse<Card[]>) => res.data),
  );

  return (
    <>
      <CreateCardContainer deck={deck} refetch={refetch} />
      {isLoading && <p>Loading...</p>}
      {isSuccess && data.map((card) => <CardContainer card={card} refetch={refetch} />)}
      {isSuccess && data.length === 0 && <p className="mt-8">This deck is empty, use the inputs above to add cards</p>}
      {isError && <CardsNotFound id={deck.id} />}
    </>
  );
};
