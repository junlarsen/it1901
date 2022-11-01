import { FC } from 'react';
import { AddCardForm } from './addCardForm';

interface deckNameProps {
  deckName: string;
}

export const AddCard: FC<deckNameProps> = ({ deckName }) => (
  <div className="bg-white border-4 p-4 shadow-md w-full">
    <h3 className="text-l font-medium">Add new card to {deckName}:</h3>
    <AddCardForm />
  </div>
);
