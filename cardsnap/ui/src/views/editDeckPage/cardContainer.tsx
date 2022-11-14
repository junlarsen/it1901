import { FC } from 'react';
import { RefetchOptions, RefetchQueryFilters, QueryObserverResult } from '@tanstack/react-query';
import { CardButtonsContainer } from './cardButtonsContainer';
import { Card } from '../../helpers/mockData';

interface CardContainerProps {
  card: Card;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<Card[]>>;
}

export const CardContainer: FC<CardContainerProps> = ({ card }) => (
  <div key={card.id} className="bg-white w-full p-4 shadow-md rounded border-white mt-8 relative">
    <h3 className="text-xl font-medium mt-3">{card.question}</h3>
    <p className="text-s font-small text-gray-600 mt-8 ">{card.answer}</p>
    <CardButtonsContainer card={card} />
  </div>
);
