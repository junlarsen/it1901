import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CardDeckCard } from '../views/homePage/cardDeckCard';
import { Subtitle } from '../components/subtitle';
import { CardDeck } from '../helpers/mockData';
import { DECKS_ENDPOINTS } from '../helpers/api';

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
            <p>You have not created any decks.</p>
          )}
        </div>
      )}
    </>
  );
};
