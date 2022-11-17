import { useQuery } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC } from 'react';
import { Link } from 'react-router-dom';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card, CardDeck } from '../../helpers/mockData';

interface CardDeckCardProps {
  cardDeck: CardDeck;
}

export const CardDeckCard: FC<CardDeckCardProps> = ({ cardDeck }) => {
  const { isLoading, data, isError, isSuccess } = useQuery(['cards', cardDeck.id], () =>
    axios.get(`${DECKS_ENDPOINTS}${cardDeck.id}/cards/`).then((res: AxiosResponse<Card[]>) => res.data),
  );

  return (
    <Link to={`/deck/${cardDeck.id}`} data-testid={`link-${cardDeck.name}`}>
      <div className="relative bg-white p-4 w-64 shadow-md rounded pb-16 border-b-4 border-white hover:border-blue-600 cursor-pointer transition-all">
        <h3 className="text-xl font-medium">{cardDeck.name}</h3>
        <p className="text-gray-700">
          {isLoading && 'Loading...'}
          {isSuccess && `${data.length} cards`}
          {isError && 'Unknown amount of cards'}
        </p>
        <figure className="absolute top-4 right-4 text-xl">🌐</figure>
      </div>
    </Link>
  );
};
