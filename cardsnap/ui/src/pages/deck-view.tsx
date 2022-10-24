import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';

export const DeckViewPage: FC = () => {
  const params = useParams<Params<'id'>>();

  return <h1 className="text-green-500">Deck view for {params.id}</h1>;
};
