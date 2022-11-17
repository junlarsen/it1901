import { useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { Subtitle } from '../components/subtitle/subtitle';
import { DECKS_ENDPOINTS } from '../helpers/api';
import { CardDeck } from '../helpers/mockData';
import { validateDeckName } from '../helpers/validation';
import { CreateDeckForm } from '../views/createDeckPage/createDeckForm';
import { FeedbackContainer } from '../views/createDeckPage/feedbackContainer';

/**
 * Renders the page for creating a new card deck
 */
export const CreateDeckPage: FC = () => {
  const [displayFeedback, setDisplayFeedback] = useState(false); // Sets if feedback-card should be displayed
  const [feedbackText, setFeedbackText] = useState(''); // The text to be shown in the feedback-card
  const [newDeckName, setNewDeckName] = useState(''); // The value that is typed in the inputfield
  const [validity, setValidity] = useState(true); // The validity og the value typed in

  /**
   * Async function to call POST request to backend for creating a new card.
   * Uses POST (http:localhost:xxxx/api/decks/) with the name as body in JSON-format
   */
  const createDeckCall = async () => {
    const res: AxiosResponse<CardDeck> = await axios.post(DECKS_ENDPOINTS, { name: newDeckName });
    return res.data;
  };

  /**
   * Makes the POST-call to backend and takes action based on the response
   */
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

  /**
   * Function to be called when user is clicking "Create deck"
   * Validates name and makes the POST-request if the name is valid.
   * Displays feedback depending on the validity of the name
   */
  const handleCreateDeck = () => {
    if (validateDeckName(newDeckName)) mutate();
    else {
      setValidity(false);
      setFeedbackText('Name can not be less than 3 characters');
    }
    setDisplayFeedback(true);
  };

  /**
   * Renders a container with input-field and a button for creating the deck
   * Renders feedback if displayFeedBack is true
   */
  return (
    <>
      <Subtitle title="Create✏️" />
      <CreateDeckForm handleCreateDeck={handleCreateDeck} newDeckName={newDeckName} setNewDeckName={setNewDeckName} />
      {displayFeedback && (
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
