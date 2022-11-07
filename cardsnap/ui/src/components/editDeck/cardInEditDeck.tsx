import { FC } from 'react';
import { CardButtons } from './cardButtons';
import { Card } from '../../helpers/mockData';

export const CardInEditDeck: FC<Card> = (card) => (
  <div className="bg-white border-4 p-4 shadow-md w-full mt-7 relative">
    <h3 className="text-xl font-medium mt-3">{card.question}</h3>
    <p className="text-s font-small text-gray-600 mt-8 ">{card.answer}</p>
    <CardButtons />
  </div>
);
