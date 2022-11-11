import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CardDeckCard } from '../views/homePage/cardDeckCard';
import { Subtitle } from '../components/subtitle';
import { CardDeck } from '../helpers/mockData';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { Button } from '../components/button';
import { Link } from 'react-router-dom';

export const HomePage: FC = () => {
  const { isLoading, error, data } = useQuery([], () =>
    axios.get(DECKS_ENDPOINTS).then((res: AxiosResponse<CardDeck[]>) => res.data),
  );

  if (error) return <p>An error has occurred.</p>;

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
