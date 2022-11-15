import { FC, useState } from 'react';
import { RefetchOptions, RefetchQueryFilters, QueryObserverResult, useMutation } from '@tanstack/react-query';
import axios, { AxiosResponse } from 'axios';
import { CardEditView } from './cardEditView';
import { CardNormalView } from './cardNormalView';
import { CardButtonsContainer } from './cardButtonsContainer';
import { Card, CardDeck } from '../../../helpers/mockData';
import { DECKS_ENDPOINTS } from '../../../helpers/api';

interface CardContainerProps {
  card: Card;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<Card[]>>;
}

export const CardContainer: FC<CardContainerProps> = ({ card, refetch }) => {
  const [editToggle, setEditToggle] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [updatedCard, setUpdatedCard] = useState({ question: card.question, answer: card.answer });

  const editCardCall = async () => {
    const res: AxiosResponse<CardDeck> = await axios.patch(
      DECKS_ENDPOINTS + card.owner + '/cards/' + card.id,
      updatedCard,
    );
    return res.data;
  };

  const { mutate } = useMutation(editCardCall, {
    onSuccess: async () => {
      await refetch();
      setErrorMessage('');
    },
    onError: () => {
      setErrorMessage('Something went wrong.');
    },
  });

  return (
    <div className="bg-white w-full p-4 shadow-md rounded border-white mt-8 relative">
      {editToggle ? (
        <CardEditView updatedCard={updatedCard} setUpdatedCard={setUpdatedCard} />
      ) : (
        <CardNormalView card={card} />
      )}
      <CardButtonsContainer
        card={card}
        refetch={refetch}
        editToggle={editToggle}
        setEditToggle={setEditToggle}
        saveMutation={mutate}
      />
      {errorMessage !== '' && <p className="text-red-500">{errorMessage}</p>}
    </div>
  );
};
