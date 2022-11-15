import { useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { Subtitle } from '../components/subtitle/subtitle';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { validateDeckName } from '../helpers/validation';
import { CreateDeckForm } from '../views/createDeckPage/createDeckForm';
import { FeedbackContainer } from '../views/createDeckPage/feedbackContainer';

export const CreateDeckPage: FC = () => {
  const [displayFeedBack, setDisplayFeedback] = useState(false);
  const [feedbackText, setFeedbackText] = useState('');
  const [newDeckName, setNewDeckName] = useState('');
  const [validity, setValidity] = useState(true);

  const createDeckCall = async () => {
    const res: AxiosResponse<CardDeck> = await axios.post(DECKS_ENDPOINTS, { name: newDeckName });
    return res.data;
  };

  const { mutate, data } = useMutation(createDeckCall, {
    onSuccess: (data) => {
      setValidity(true);
      setFeedbackText(`Deck with name ${data.name} was added!`);
      setNewDeckName('');
    },
    onError: () => {
      setValidity(false);
      setFeedbackText('There was a problem when creating this deck.');
    },
  });

  const handleCreateDeck = () => {
    if (validateDeckName(newDeckName)) mutate();
    else {
      setValidity(false);
      setFeedbackText('Name can not be less than 3 characters');
    }
    setDisplayFeedback(true);
  };

  return (
    <>
      <Subtitle title="Create✏️" />
      <CreateDeckForm handleCreateDeck={handleCreateDeck} newDeckName={newDeckName} setNewDeckName={setNewDeckName} />
      {displayFeedBack && (
        <FeedbackContainer
          setDisplayFeedback={setDisplayFeedback}
          feedbackText={feedbackText}
          validity={validity}
          data={data}
        />
      )}
    </>
  );
};
