import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { DeckNotFound } from '../components/deckNotFound/deckNotFound';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { DeckContainer } from '../views/deckPage/deckContainer';

/**
 * Page to be displayed when user is selecting a deck at the homepage.
 */
export const DeckPage: FC = () => {
  // Gets deck id from URL
  const params = useParams<Params<'id'>>();

  /**
   * Gets a deck from the API with the given ID
   */
  const { isLoading, data, isSuccess, isError } = useQuery(['deck', params.id], () =>
    axios.get(`${DECKS_ENDPOINTS}${params.id ?? ''}`).then((res: AxiosResponse<CardDeck>) => res.data),
  );

  /**
   * Renders content based on how the API call to backend resulted.
   */
  return (
    <>
      {isLoading && <p>Loading...</p>}
      {isSuccess && <DeckContainer cardDeck={data} />}
      {isError && <DeckNotFound id={params.id} />}
    </>
  );
};
