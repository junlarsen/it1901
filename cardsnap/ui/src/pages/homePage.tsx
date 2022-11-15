import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { Link } from 'react-router-dom';
import { CardDeckCard } from '../views/homePage/cardDeckCard';
import { Subtitle } from '../components/subtitle/subtitle';
import { CardDeck } from '../helpers/mockData';
import { DECKS_ENDPOINTS } from '../helpers/api';

export const HomePage: FC = () => {
  const { isLoading, isError, data } = useQuery(['decks'], () =>
    axios.get(DECKS_ENDPOINTS).then((res: AxiosResponse<CardDeck[]>) => res.data),
  );

  if (isError) {
    return (
      <>
        <Subtitle title="Home🏡" />
        <p>An error has occurred.</p>
      </>
    );
  }

  return (
    <>
      <Subtitle title="Home🏡" />
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <div className="flex gap-8 flex-wrap">
          {data && data.length > 0 ? (
            data.map((cardDeck) => <CardDeckCard key={cardDeck.name} cardDeck={cardDeck} />)
          ) : (
            <div>
              <p>You don't have any decks yet.</p>
              <p className="mt-4">
                For adding decks, visit{' '}
                <Link to="/create" className="text-blue-500 hover:pointer">
                  Create
                </Link>
              </p>
            </div>
          )}
        </div>
      )}
    </>
  );
};
