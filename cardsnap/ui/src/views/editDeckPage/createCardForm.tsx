import { QueryObserverResult, RefetchOptions, RefetchQueryFilters, useMutation } from '@tanstack/react-query';
import axios from 'axios';
import { FC, useState } from 'react';
import { Button } from '../../components/button/button';
import { DECKS_ENDPOINTS } from '../../helpers/api';
import { Card, CardDeck } from '../../helpers/mockData';
import { validateAnswer, validateQuestion } from '../../helpers/validation';

interface CreateCardForm {
  deck: CardDeck;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<Card[]>>;
}

/**
 * Form for adding a new card to the deck
 * @param deck Deck to get new card
 * @param refetch Function to refetch the API call to the REST API
 */
export const CreateCardForm: FC<CreateCardForm> = ({ deck, refetch }) => {
  const [newQuestion, setNewQuestion] = useState('');
  const [newAnswer, setNewAnswer] = useState('');
  const [questionValidity, setQuestionValidity] = useState(true);
  const [answerValidity, setAnswerValidity] = useState(true);
  const [feedback, setFeedback] = useState('');

  /**
   * Adds new card to the deck with a API call to the REST API
   * POST(Localhost:xxxx/api/decks/{deckid}/cards/)
   * with question and answer as body in JSON format
   */
  const createCardCall = async () => {
    await axios.post(`${DECKS_ENDPOINTS}${deck.id}/cards/`, {
      question: newQuestion,
      answer: newAnswer,
    });
  };

  /**
   * Makes the POST call to the API and behaves after how the call went
   */
  const { mutate } = useMutation(createCardCall, {
    onSuccess: async () => {
      await refetch();
      setFeedback('The new card was added!');
      setNewQuestion('');
      setNewAnswer('');
      setQuestionValidity(true);
      setAnswerValidity(true);
    },
    onError: () => {
      setFeedback('Oops, an error occured wile adding a new card...');
    },
  });

  /**
   * Function to be called when user is clicking the add card button
   */
  const handleAddCard = () => {
    if (validateQuestion(newQuestion) && validateAnswer(newAnswer)) {
      mutate();
    } else {
      setFeedback('Error, fields can not be empty');
      setQuestionValidity(validateQuestion(newQuestion));
      setAnswerValidity(validateAnswer(newAnswer));
    }
  };

  /**
   * Renders inputfields for question and answer and button for adding the card.
   */
  return (
    <>
      <input
        type="text"
        placeholder="Question"
        className={`w-full mt-4 mr-4 bg-white border border-gray-300 focus:ring-blue-500 focus:border-blue-500 p-2 ${
          questionValidity ? '' : 'border-red-500'
        }`}
        value={newQuestion}
        onChange={(evt) => setNewQuestion(evt.target.value)}
      />
      <textarea
        rows={3}
        placeholder="Answer"
        className={`w-full my-4 mr-4 bg-white border border-gray-300 focus:ring-blue-500 focus:border-blue-500 p-2 ${
          answerValidity ? '' : 'border-red-500'
        }`}
        value={newAnswer}
        onChange={(evt) => setNewAnswer(evt.target.value)}
      />
      <Button label="Add card" clickHandler={handleAddCard}></Button>
      <label className="ml-4">{feedback}</label>
    </>
  );
};
