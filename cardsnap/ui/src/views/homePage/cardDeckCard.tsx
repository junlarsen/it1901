import { FC } from 'react';
import { Link } from 'react-router-dom';
import { CardDeck } from '../../helpers/mockData';
import { Button } from '../../components/button';

interface CardDeckCardProps {
  cardDeck: CardDeck;
}

export const CardDeckCard: FC<CardDeckCardProps> = ({ cardDeck }) => (
  <div className="relative bg-white border-4 p-4 w-76 shadow-md">
    <h3 className="text-xl font-medium">{cardDeck.name}</h3>
    <p className="text-gray-700">? cards</p>
    <figure className="absolute top-4 right-4 text-xl">🌐</figure>
    <div className="flex gap-4 justify-center mt-4">
      <Link to={`/edit/${cardDeck.id}`}>
        <Button label="Edit" />
      </Link>
      <Link to={`/play/${cardDeck.id}`}>
        <Button label="Play" />
      </Link>
    </div>
  </div>
);
