import { FC } from 'react';
import { Link } from 'react-router-dom';
import { CardDeck } from '../../helpers/mockData';

interface CardDeckCardProps {
  cardDeck: CardDeck;
}

export const CardDeckCard: FC<CardDeckCardProps> = ({ cardDeck }) => (
  <Link to={`/deck/${cardDeck.id}`}>
    <div className="relative bg-white p-4 w-64 shadow-md rounded pb-16 border-b-4 border-white hover:border-blue-600 cursor-pointer transition-all">
      <h3 className="text-xl font-medium">{cardDeck.name}</h3>
      <p className="text-gray-700">? cards</p>
      <figure className="absolute top-4 right-4 text-xl">🌐</figure>
    </div>
  </Link>
);
