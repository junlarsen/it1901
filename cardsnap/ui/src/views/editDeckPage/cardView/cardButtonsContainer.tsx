import {
  QueryObserverResult,
  RefetchOptions,
  RefetchQueryFilters,
  UseMutateFunction,
  useMutation,
} from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { Button } from '../../../components/button';
import { PenToggle } from '../../../components/penToggle';
import { DECKS_ENDPOINTS } from '../../../helpers/api';
import { Card, CardDeck } from '../../../helpers/mockData';

interface CardButtonsContainerProps {
  card: Card;
  editToggle: boolean;
  setEditToggle: React.Dispatch<React.SetStateAction<boolean>>;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<Card[]>>;
  saveMutation?: UseMutateFunction<CardDeck>;
}

export const CardButtonsContainer: FC<CardButtonsContainerProps> = ({
  card,
  editToggle,
  setEditToggle,
  refetch,
  saveMutation,
}) => {
  const [feedbackText, setFeedbackText] = useState('');

  const deleteCardCall = async () => {
    const res: AxiosResponse<CardDeck> = await axios.delete(DECKS_ENDPOINTS + card.owner + '/cards/' + card.id);
    return res.data;
  };

  const { mutate } = useMutation(deleteCardCall, {
    onSuccess: async () => {
      await refetch();
      setFeedbackText('');
    },
    onError: () => {
      setFeedbackText('There was a problem when creating this deck.');
    },
  });

  const editCardHandler = () => {
    setEditToggle(!editToggle);
    setFeedbackText('');
  };

  const deleteCardHandler = () => {
    setFeedbackText('');
    mutate();
  };

  const handleSaveClick = () => {
    if (saveMutation) {
      saveMutation();
    }
    setEditToggle(false);
  };

  return (
    <div className="top-4 absolute right-4 ">
      <div className="flex flex-col gap-2 items-end">
        <div className="flex gap-4">
          {editToggle && <Button clickHandler={deleteCardHandler} label="Delete" type="delete" />}
          <PenToggle clickHandler={editCardHandler} editToggle={editToggle} />
        </div>
        {editToggle && <Button label="Save" clickHandler={handleSaveClick} />}
        <p className="text-right text-red-500">{feedbackText}</p>
      </div>
    </div>
  );
};
