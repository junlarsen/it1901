import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { CreateCardContainer } from '../views/editDeckPage/createCardContainer';
import { CardContainer } from '../views/editDeckPage/cardContainer';
import { EditDeckPageHeader } from '../views/editDeckPage/editDeckPageHeader/editDeckPageHeader';

export const EditDeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  const sampleCards = [{ question: 'Whats up!', answer: 'Not much :3' }];

  return (
    <>
      <EditDeckPageHeader name={params.id!} />
      <CreateCardContainer deckName={params.id!} />
      {sampleCards.map((card) => (
        <CardContainer card={card} />
      ))}
    </>
  );
};
