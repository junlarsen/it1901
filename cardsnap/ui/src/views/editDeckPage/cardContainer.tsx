import { FC } from 'react';
import { CardButtonsContainer } from './cardButtonsContainer';
import { Card } from '../../helpers/mockData';

interface CardContainerProps {
  card: Card;
}

export const CardContainer: FC<CardContainerProps> = ({ card }) => (
  <div className="bg-white border-4 p-4 shadow-md w-full mt-7 relative">
    <h3 className="text-xl font-medium mt-3">{card.question}</h3>
    <p className="text-s font-small text-gray-600 mt-8 ">{card.answer}</p>
    <CardButtonsContainer />
  </div>
);
