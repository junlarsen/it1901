import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { Subtitle } from '../components/subtitle';

export const DeckPage: FC = () => {
  const params = useParams<Params<'id'>>();

  return (
    <>
      <Subtitle title="Le name of deck" />
      <p>Deck with id: {params.id}</p>
      <p>You should be able to both play and edit this deck😧</p>
    </>
  );
};
