import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { AddCard } from '../components/editDeck/addCard';
import { CardInEditDeck } from '../components/editDeck/cardInEditDeck';
import { Subtitle } from '../components/subtitle';

export const EditDeckView: FC = () => {
  const params = useParams<Params<'id'>>();

  return (
    <>
      <Subtitle title={'Edit ' + params.id!} />
      <AddCard deckName={params.id!} />
      <CardInEditDeck question="Lorem lorem lorem lorem" answer="ipsum ipsum ipsum ipsum" />
      <CardInEditDeck question="Lorem2 lorem lorem lorem" answer="ipsum2 ipsum ipsum ipsum" />
      <CardInEditDeck question="Lorem3 lorem lorem lorem" answer="ipsum3 ipsum ipsum ipsum" />
    </>
  );
};
