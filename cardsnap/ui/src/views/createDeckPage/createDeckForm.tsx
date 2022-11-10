import { FC, useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FeecbackContainer } from './feedbackContainer';
import { Button } from '../../components/button';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { CardDeck } from '../../helpers/mockData';

export const CreateDeckForm: FC = () => {
  const [displayFeedBack, setDisplayFeedback] = useState(false);
  const [feedbackText, setFeedbackText] = useState('');
  const [newDeckName, setNewDeckName] = useState('');

  const createDeckCall = async () => {
    const res: AxiosResponse<CardDeck> = await axios.post(DECKS_ENDPOINTS, { name: newDeckName });
    return res.data;
  };

  const { mutate, isSuccess, data } = useMutation(createDeckCall, {
    onSuccess: (data) => {
      setFeedbackText(`Deck with name ${data.name} was added!`);
      setDisplayFeedback(true);
      setNewDeckName('');
    },
    onError: () => {
      setFeedbackText('There was a problem when creating this deck.');
      setDisplayFeedback(true);
    },
  });

  return (
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
      <Button label="Create" clickHandler={mutate} />
      {displayFeedBack && (
        <FeecbackContainer
          setDisplayFeedback={setDisplayFeedback}
          feedbackText={feedbackText}
          isSucess={isSuccess}
          data={data}
        />
      )}
    </>
  );
};
