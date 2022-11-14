import { FC } from 'react';
import { CardContainer } from '../../views/editDeckPage/cardContainer';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card } from '../../helpers/mockData';
import { CardsNotFound } from '../../components/cardsNotFound';

interface ListOfCardsProps {
  id: string;
}

export const ListOfCards: FC<ListOfCardsProps> = ({ id }) => {
  const { isLoading, data, isSuccess, isError } = useQuery(['cards', id], () =>
    axios.get(DECKS_ENDPOINTS + id + '/cards/').then((res: AxiosResponse<Card[]>) => res.data),
  );

  return (
    <>
      {isLoading && <p>Loading...</p>}
      {isSuccess && data.length > 0 && data.map((card) => <CardContainer card={card} />)}
      {isSuccess && data.length == 0 && <p className="mt-8">This deck is empty, use the inputs above to add cards</p>}
      {isError && <CardsNotFound id={id} />}
    </>
  );
};
