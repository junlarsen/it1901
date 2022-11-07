import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { CreateCardContainer } from '../views/editDeckPage/createCardContainer';
import { CardContainer } from '../views/editDeckPage/cardContainer';
import { Subtitle } from '../components/subtitle';
import { cardDecks } from '../helpers/mockData';

export const EditDeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  return (
    <>
      <Subtitle title={'Edit ' + params.id!} />
      <CreateCardContainer deckName={params.id!} />
      {cardDecks[0].cards.map((card) => (
        <CardContainer card={card} />
      ))}
    </>
  );
};
