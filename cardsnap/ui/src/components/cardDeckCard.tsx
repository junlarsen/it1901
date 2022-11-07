import { FC } from 'react';
import { DeckButton } from './deckViewButton';
import { CardDeck } from '../helpers/mockData';

interface CardDeckCardProps {
  cardDeck: CardDeck;
}

export const CardDeckCard: FC<CardDeckCardProps> = ({ cardDeck }) => (
  <div className="relative bg-white border-4 p-4 w-76 shadow-md">
    <h3 className="text-xl font-medium">{cardDeck.name}</h3>
    <p className="text-gray-700">{cardDeck.cards.length} cards</p>
    <figure className="absolute top-4 right-4 text-xl">🌐</figure>
    <div className="flex gap-4 justify-center mt-4">
      <DeckButton cardDeckName={cardDeck.name} type={'edit'} />
      <DeckButton cardDeckName={cardDeck.name} type={'play'} />
    </div>
  </div>
);
