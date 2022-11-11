import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { DeckNotFound } from '../components/deckNotFound';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { DeckContainer } from '../views/deckPage/deckContainer';

export const DeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  const { isLoading, data, isSuccess, isError } = useQuery([], () =>
    axios.get(DECKS_ENDPOINTS + params.id).then((res: AxiosResponse<CardDeck>) => res.data),
  );

  return (
    <>
      {isLoading && <p>Loading...</p>}
      {isSuccess && <DeckContainer cardDeck={data} />}
      {isError && <DeckNotFound id={params.id} />}
    </>
  );
};
