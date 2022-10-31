import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { PlayCardCard } from '../components/playCardCard';
import { Subtitle } from '../components/subtitle';

export const EditDeckView: FC = () => {
  const params = useParams<Params<'id'>>();

  return (
    <>
      <Subtitle title={'Edit ' + params.id!} />
      <p>Soon to get some content to edit deck</p>
    </>
  );
};
