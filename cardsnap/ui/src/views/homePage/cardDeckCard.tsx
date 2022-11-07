import { FC } from 'react';
import { CardDeck } from '../../helpers/mockData';
import { Button } from '../../components/button';

interface CardDeckCardProps {
  cardDeck: CardDeck;
}

export const CardDeckCard: FC<CardDeckCardProps> = ({ cardDeck }) => {
  const editClickHandler = () => {
    window.location.pathname = window.location.pathname + `edit/${cardDeck.name}`;
  };

  const playClickHandler = () => {
    window.location.pathname = window.location.pathname + `play/${cardDeck.name}`;
  };

  return (
    <div className="relative bg-white border-4 p-4 w-76 shadow-md">
      <h3 className="text-xl font-medium">{cardDeck.name}</h3>
      <p className="text-gray-700">{cardDeck.cards.length} cards</p>
      <figure className="absolute top-4 right-4 text-xl">🌐</figure>
      <div className="flex gap-4 justify-center mt-4">
        <Button label="Edit" clickHandler={editClickHandler} />
        <Button label="Play" clickHandler={playClickHandler} />
      </div>
    </div>
  );
};
