import { FC } from 'react';
import { RefetchOptions, RefetchQueryFilters, QueryObserverResult } from '@tanstack/react-query';
import { CreateCardForm } from './createCardForm';
import { Card, CardDeck } from '../../helpers/mockData';

interface CreateCardContainerProps {
  deck: CardDeck;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<Card[]>>;
}

export const CreateCardContainer: FC<CreateCardContainerProps> = ({ deck, refetch }) => (
  <div className="bg-white p-4 shadow-md rounded border-b-4 border-white w-full">
    <h3 className="text-l font-medium">Add new card to {deck.name}</h3>
    <CreateCardForm deck={deck} refetch={refetch} />
  </div>
);
