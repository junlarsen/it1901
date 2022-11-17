import {
  QueryObserverResult,
  RefetchOptions,
  RefetchQueryFilters,
  UseMutateFunction,
  useMutation,
} from '@tanstack/react-query';
import axios from 'axios';
import { FC, useState } from 'react';
import { Button } from '../../../components/button/button';
import { PenToggle } from '../../../components/penToggle/penToggle';
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

/**
 * Renders a container with buttons for edit/delete a card
 * @param card Card to connect the buttons to
 * @param editToggle boolean true if edit-view is displayed
 * @param setEditToggle SetStateAction<boolean> to set editview
 * @param refetch Function to refetch API call for getting cards
 * @param saveMutation UseMutateFunction<CardDeck> to update the card
 */
export const CardButtonsContainer: FC<CardButtonsContainerProps> = ({
  card,
  editToggle,
  setEditToggle,
  refetch,
  saveMutation,
}) => {
  const [feedbackText, setFeedbackText] = useState('');

  /**
   * API call to delete a given card from the deck.
   * DELETE(localhost:xxxx/api/decks/{deckid}/cards/{cardid})
   */
  const deleteCardCall = async () => {
    await axios.delete(DECKS_ENDPOINTS + card.owner + '/cards/' + card.id);
  };

  /**
   * Calls on the API call and behaves after how the response on the call.
   */
  const { mutate } = useMutation(deleteCardCall, {
    onSuccess: async () => {
      await refetch();
      setFeedbackText('');
    },
    onError: () => {
      setFeedbackText('There was a problem when creating this deck.');
    },
  });

  /**
   * Handles click on edit button
   */
  const editCardHandler = () => {
    setEditToggle(!editToggle);
    setFeedbackText('');
  };

  /**
   * Handles click on delete card button
   */
  const deleteCardHandler = () => {
    setFeedbackText('');
    mutate();
  };

  /**
   * Handles click on save card button
   */
  const handleSaveClick = () => {
    if (saveMutation) {
      saveMutation();
    }
    setEditToggle(false);
  };

  /**
   * Renders a div with the buttons
   */
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
