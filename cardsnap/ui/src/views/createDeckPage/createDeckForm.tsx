import { useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
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
      setFeedbackText(`${data.name} was added!`);
      setDisplayFeedback(true);
      setNewDeckName('');
    },
    onError: () => {
      setFeedbackText('Error when creating deck');
      setDisplayFeedback(true);
    },
  });

  return (
    <>
      <div className="bg-white border-4 p-4 w-80 shadow-md">
        <label htmlFor="deckName" className="block mb-4 font-medium text-gray-900">
          Name
        </label>
        <input
          type="text"
          id="deckName"
          name="deckName"
          placeholder="New deckname"
          className="w-full mb-4 bg-gray-50 border border-gray-300 text-gray-900 focus:ring-blue-500 focus:border-blue-500 p-2"
          value={newDeckName}
          onChange={(evt) => setNewDeckName(evt.target.value)}
          required
        />
        <Button label="Create" clickHandler={mutate} />
      </div>
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
