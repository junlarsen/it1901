import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { EditDeckPageHeader } from '../views/editDeckPage/editDeckPageHeader/editDeckPageHeader';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { DeckNotFound } from '../components/deckNotFound/deckNotFound';
import { CardDeckContent } from '../views/editDeckPage/cardDeckContent';
import { DeleteDeckButton } from '../views/editDeckPage/deleteDeckButton';

export const EditDeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  const { isLoading, data, isSuccess, isError, refetch } = useQuery(['deck', params.id], () =>
    axios.get(`${DECKS_ENDPOINTS}${params.id ?? ''}`).then((res: AxiosResponse<CardDeck>) => res.data),
  );

  return (
    <>
      <>
        {isLoading && <p>Loading...</p>}
        {isSuccess && (
          <>
            <EditDeckPageHeader deck={data} refetch={refetch} />
            <CardDeckContent deck={data} />
            <DeleteDeckButton deck={data} />
          </>
        )}
        {isError && <DeckNotFound id={params.id} />}
      </>
    </>
  );
};
