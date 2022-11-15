import { FC } from 'react';
import { Card } from '../../../helpers/mockData';

interface CardNormalViewProps {
  card: Card;
}

export const CardNormalView: FC<CardNormalViewProps> = ({ card }) => (
  <div className="flex flex-col md:flex-row gap-4 h-full md:items-center">
    <p className="w-1/2 md:w-1/3 py-2 border-b-2 border-none">{card.question}</p>
    <p className="w-1/2 py-2 border-b-2 border-none">{card.answer}</p>
  </div>
);
