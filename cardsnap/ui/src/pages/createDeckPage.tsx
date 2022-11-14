import { useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { Subtitle } from '../components/subtitle';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { CreateDeckForm } from '../views/createDeckPage/createDeckForm';
import { FeedbackContainer } from '../views/createDeckPage/feedbackContainer';

export const CreateDeckPage: FC = () => {
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
      <Subtitle title="Create✏️" />
      <CreateDeckForm mutate={mutate} newDeckName={newDeckName} setNewDeckName={setNewDeckName} />
      {displayFeedBack && (
        <FeedbackContainer
          setDisplayFeedback={setDisplayFeedback}
          feedbackText={feedbackText}
          isSucess={isSuccess}
          data={data}
        />
      )}
    </>
  );
};
