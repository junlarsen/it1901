import { FC } from 'react';
import { CreateCardForm } from './createCardForm';

interface CreateCardContainerProps {
  deckName: string;
}

export const CreateCardContainer: FC<CreateCardContainerProps> = ({ deckName }) => (
  <div className="bg-white border-4 p-4 shadow-md w-full">
    <h3 className="text-l font-medium">Add new card to {deckName}:</h3>
    <CreateCardForm />
  </div>
);
