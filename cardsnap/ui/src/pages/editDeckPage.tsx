import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { CreateCardContainer } from '../views/editDeckPage/createCardContainer';
import { CardContainer } from '../views/editDeckPage/cardContainer';
import { EditDeckPageHeader } from '../views/editDeckPage/editDeckPageHeader/editDeckPageHeader';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { DeckNotFound } from '../components/deckNotFound';
import { ListOfCards } from '../views/editDeckPage/listOfCards';

export const EditDeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  const { isLoading, data, isSuccess, isError } = useQuery(['deck', params.id], () =>
    axios.get(DECKS_ENDPOINTS + params.id).then((res: AxiosResponse<CardDeck>) => res.data),
  );

  return (
    <>
      <>
        {isLoading && <p>Loading...</p>}
        {isSuccess && (
          <>
            <EditDeckPageHeader name={data.name} />
            <CreateCardContainer deckName={data.name} />
            <ListOfCards id={data.id} />
          </>
        )}
        {isError && <DeckNotFound id={params.id} />}
      </>
    </>
  );
};
