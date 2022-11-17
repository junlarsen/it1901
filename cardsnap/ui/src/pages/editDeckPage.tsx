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

/**
 * Page to be displayed whem user is selecting edit at the deckpage.
 */
export const EditDeckPage: FC = () => {
  // Gets Deck ID from the URL
  const params = useParams<Params<'id'>>();

  // Gets the deck with id from the URL
  const { isLoading, data, isSuccess, isError, refetch } = useQuery(['deck', params.id], () =>
    axios.get(`${DECKS_ENDPOINTS}${params.id ?? ''}`).then((res: AxiosResponse<CardDeck>) => res.data),
  );

  /**
   * Renders content depending on the response on the API call.
   */
  return (
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
  );
};
