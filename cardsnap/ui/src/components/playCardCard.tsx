import { FC } from 'react';
import { Card } from '../helpers/mockData';

interface CardProps {
  card: Card;
  currentCount: number;
  totalCount: number;
}

export const PlayCardCard: FC<CardProps> = ({ card, currentCount, totalCount }) => (
  <div className="bg-white border-4 p-4 shadow-md max-w-2xl mr-8 pt-6 pb-8">
    <p className="font-thin">
      {currentCount}/{totalCount}
    </p>
    <div className="bg-sky-500 w-full h-2">
      <div className="bg-orange-500 h-2" style={{ width: `${(currentCount / totalCount) * 100}%` }} />
    </div>
    <h3 className="text-2xl my-4 font-medium">{card.question}</h3>
    <p className="text-gray-700 text-2xl my-4">{card.answer} (To be hidden)</p>
  </div>
);
