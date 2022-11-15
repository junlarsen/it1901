import { FC } from 'react';
import { Button } from '../../components/button/button';

interface CreateDeckFormProps {
  newDeckName: string;
  handleCreateDeck: () => void;
  setNewDeckName: React.Dispatch<React.SetStateAction<string>>;
}

export const CreateDeckForm: FC<CreateDeckFormProps> = ({ newDeckName, setNewDeckName, handleCreateDeck }) => (
  <>
    <span className="text-xl font-medium">Name</span>
    <p className="text-gray-600">Enter the name of the deck you want to create.</p>
    <input
      type="text"
      placeholder="AlgDat"
      className="my-4 mr-4 bg-white border border-gray-300 max-w-sm focus:ring-blue-500 focus:border-blue-500 p-2 w-full"
      value={newDeckName}
      onChange={(evt) => setNewDeckName(evt.target.value)}
    />
    <Button label="Create" clickHandler={handleCreateDeck} />
  </>
);
