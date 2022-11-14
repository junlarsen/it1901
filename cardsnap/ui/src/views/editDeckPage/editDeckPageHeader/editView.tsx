import { QueryObserverResult, RefetchOptions, RefetchQueryFilters, useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { Button } from '../../../components/button';
import { DECKS_ENDPOINTS } from '../../../helpers/api';
import { CardDeck } from '../../../helpers/mockData';
import { validateDeckName } from '../../../helpers/validation';

interface EditViewProps {
  deck: CardDeck;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<CardDeck>>;
  setEditToggle: React.Dispatch<React.SetStateAction<boolean>>;
}

export const EditView: FC<EditViewProps> = ({ deck, setEditToggle, refetch }) => {
  const [updatedName, setUpdatedName] = useState(deck.name);
  const [nameValidity, setNameValidity] = useState(true);

  const createDeckCall = async () => {
    const res: AxiosResponse<CardDeck> = await axios.patch(DECKS_ENDPOINTS + '/' + deck.id, { name: updatedName });
    return res.data;
  };

  const { mutate } = useMutation(createDeckCall, {
    onSuccess: () => {
      setNameValidity(true);
      void refetch();
    },
    onError: () => {
      setNameValidity(false);
    },
  });

  const handleSave = () => {
    if (validateDeckName(updatedName)) {
      mutate();
      setEditToggle(false);
    } else {
      setNameValidity(false);
    }
  };

  const handleCancelPress = () => {
    setEditToggle(false);
  };

  return (
    <div className="flex items-start h-8  gap-4">
      <input
        type="text"
        className={`bg-gray-50 border-2 border-gray-300 text-gray-900 w-64 p-2 ${nameValidity ? '' : 'border-red-500'}`}
        value={updatedName}
        onChange={(evt) => setUpdatedName(evt.target.value)}
      />
      <Button clickHandler={handleSave} label="Save" />
      <Button clickHandler={handleCancelPress} label="Cancel" type="gray" />
    </div>
  );
};
