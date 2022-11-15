import { QueryObserverResult, RefetchOptions, RefetchQueryFilters, useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
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

export const CreateCardForm: FC<CreateCardForm> = ({ deck, refetch }) => {
  const [newQuestion, setNewQuestion] = useState('');
  const [newAnswer, setNewAnswer] = useState('');
  const [questionValidity, setQuestionValidity] = useState(true);
  const [answerValidity, setAnswerValidity] = useState(true);
  const [feedback, setFeedback] = useState('');

  const createCardCall = async () => {
    const res: AxiosResponse<Card> = await axios.post(`${DECKS_ENDPOINTS}${deck.id}/cards/`, {
      question: newQuestion,
      answer: newAnswer,
    });
    return res.data;
  };

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

  const handleAddCard = () => {
    if (validateQuestion(newQuestion) && validateAnswer(newAnswer)) {
      mutate();
    } else {
      setFeedback('Error, fields can not be empty');
      setQuestionValidity(validateQuestion(newQuestion));
      setAnswerValidity(validateAnswer(newAnswer));
    }
  };

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
