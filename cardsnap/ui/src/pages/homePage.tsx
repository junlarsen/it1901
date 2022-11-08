import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CardDeckCard } from '../views/homePage/cardDeckCard';
import { Subtitle } from '../components/subtitle';
import { cardDecks as mockCardDecks, TmpCardDeck } from '../helpers/mockData';
import { DECKS_ENDPOINTS } from '../helpers/api';

export const HomePage: FC = () => {
  const { isLoading, error, data } = useQuery([], () =>
    axios.get(DECKS_ENDPOINTS).then((res: AxiosResponse<TmpCardDeck[]>) => res.data),
  );

  if (error) return <p>An error has occurred.</p>;

  return (
    <>
      <Subtitle title="Decks" />
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <div className="flex gap-4 max-w-2xl flex-wrap">
          {mockCardDecks.map((cardDeck) => (
            <CardDeckCard key={cardDeck.name} cardDeck={cardDeck} />
          ))}
        </div>
      )}
    </>
  );
};
