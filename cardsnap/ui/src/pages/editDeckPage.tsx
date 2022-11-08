import { FC, useState } from 'react';
import { Params, useParams } from 'react-router-dom';
import { CreateCardContainer } from '../views/editDeckPage/createCardContainer';
import { CardContainer } from '../views/editDeckPage/cardContainer';
import { cardDecks } from '../helpers/mockData';
import { EditDeckPageHeader } from '../views/editDeckPage/editDeckPageHeader/editDeckPageHeader';

export const EditDeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  return (
    <>
      <EditDeckPageHeader name={params.id!} />
      <CreateCardContainer deckName={params.id!} />
      {cardDecks[0].cards.map((card) => (
        <CardContainer card={card} />
      ))}
    </>
  );
};
