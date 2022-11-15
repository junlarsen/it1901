import { QueryObserverResult, RefetchOptions, RefetchQueryFilters, useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { FC, useState } from 'react';
import { Button } from '../../components/button';
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
    onSuccess: async (data) => {
      await refetch();
      setFeedback(`A new card was added: {Q: ${data.question} | A: ${data.answer}}`);
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
        className={`mt-4 bg-gray-50 border border-gray-300 text-gray-900 text-sm block p-2.5 w-full ${
          questionValidity ? '' : 'border-red-500'
        }`}
        value={newQuestion}
        onChange={(evt) => setNewQuestion(evt.target.value)}
      />
      <textarea
        rows={3}
        placeholder="Answer"
        className={`mt-4 bg-gray-50 border border-gray-300 text-gray-900 text-sm block p-2.5 w-full mb-4 ${
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
