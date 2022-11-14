import { FC } from 'react';
import { CreateCardForm } from './createCardForm';

interface CreateCardContainerProps {
  deckName: string;
}

export const CreateCardContainer: FC<CreateCardContainerProps> = ({ deckName }) => (
  <div className="bg-white p-4 shadow-md rounded border-b-4 border-white w-full">
    <h3 className="text-l font-medium">Add new card to {deckName}:</h3>
    <CreateCardForm />
  </div>
);
