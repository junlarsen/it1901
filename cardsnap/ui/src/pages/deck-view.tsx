import { FC } from 'react';
import { Params, useParams } from 'react-router-dom';
import { PlayCardCard } from '../components/playCardCard';
import { PlayButtons } from '../components/playButtons';
import { Subtitle } from '../components/subtitle';

export const DeckView: FC = () => {
  const params = useParams<Params<'id'>>();

  return (
    <>
      <Subtitle title={'Play ' + params.id!} />
      <div className="w-3/4 ml-auto mr-auto p-16">
        <div className="ml-auto mr-auto w-full min-w-200">
          <PlayCardCard card={{ question: 'FOO', answer: 'BAR' }} currentCount={5} totalCount={20} />
          <PlayButtons />
        </div>
      </div>
    </>
  );
};
