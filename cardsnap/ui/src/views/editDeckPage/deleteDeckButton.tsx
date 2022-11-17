import { useMutation } from '@tanstack/react-query';
import axios from 'axios';
import { FC, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '../../components/button/button';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { CardDeck } from '../../helpers/mockData';

interface DeleteDeckButtonProps {
  deck: CardDeck;
}

/**
 * Renders the delete deck button for deletion of a deck.
 * @param deck CardDeck to be deleted
 */
export const DeleteDeckButton: FC<DeleteDeckButtonProps> = ({ deck }) => {
  const [displayConfirmation, setDisplayConfirmation] = useState(false);
  const navigate = useNavigate();

  /**
   * Makes the API call for deletion of the deck.
   * DELETE(Localhost:xxxx/api/decks/{deckid})
   */
  const deleteDeckCall = async () => {
    await axios.delete(DECKS_ENDPOINTS + deck.id);
  };

  /**
   * Deletes deck and takes user to homepage if success.
   */
  const { mutate, isError } = useMutation(deleteDeckCall, {
    onSuccess: () => {
      navigate('/');
    },
  });

  /**
   * Clickhandlers
   */
  const handleYesClick = () => mutate();
  const handleNoClick = () => setDisplayConfirmation(false);
  const deleteClickHandler = () => setDisplayConfirmation(true);

  /**
   * Renders delete button or box for confirmation of the deletion
   */
  return (
    <div className="mt-8">
      {displayConfirmation ? (
        <div className="bg-white rounded p-4 max-w-sm shadow-md mt-4">
          <p className="mb-4">Are you sure you want to delete the card deck?</p>
          <div className="flex gap-4">
            <Button label="Yes" type="red" clickHandler={handleYesClick} />
            <Button label="No" type="gray" clickHandler={handleNoClick}></Button>
          </div>
        </div>
      ) : (
        <Button clickHandler={deleteClickHandler} label="Delete Deck" type="red" />
      )}
      {isError && <p>Something went wrong!</p>}
    </div>
  );
};
