import { useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { redirect, useNavigate } from 'react-router-dom';
import { Button } from '../../components/button';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { CardDeck } from '../../helpers/mockData';

interface DeleteDeckButtonProps {
  deck: CardDeck;
}

export const DeleteDeckButton: FC<DeleteDeckButtonProps> = ({ deck }) => {
  const [displayConfirmation, setDisplayConfirmation] = useState(false);
  const navigate = useNavigate();

  const deleteDeckCall = async () => {
    await axios.delete(DECKS_ENDPOINTS + deck.id);
  };

  const { mutate, isError } = useMutation(deleteDeckCall, {
    onSuccess: () => {
      navigate('/');
    },
  });

  const handleYesClick = () => mutate();
  const handleNoClick = () => setDisplayConfirmation(false);
  const deleteClickHandler = () => setDisplayConfirmation(true);

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
