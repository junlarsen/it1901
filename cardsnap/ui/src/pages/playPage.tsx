import { FC, useState } from 'react';
import { Params, useParams } from 'react-router-dom';
import { PlayCardCard } from '../views/playPage/playCardCard';
import { PlayButtons } from '../views/playPage/playButtons';
import { Subtitle } from '../components/subtitle';

export const PlayPage: FC = () => {
  const [displayAnswer, setDisplayAnswer] = useState(false);
  const params = useParams<Params<'id'>>();

  return (
    <>
      <Subtitle title={'Play ' + params.id!} />
      <div className="w-3/4 ml-auto mr-auto p-16">
        <div className="ml-auto mr-auto w-full min-w-200">
          <PlayCardCard
            card={{
              question: 'How many days until Christmas?',
              answer:
                'Lorem ipsum dolor sit amet consectetur adipisicing elit. Amet nobis esse maiores quos explicabo autem non molestiae sit sequi doloribus, quibusdam, nisi blanditiis quod dolorum vitae quae cum consectetur veritatis.',
            }}
            currentCount={5}
            totalCount={20}
            displayAnswer={displayAnswer}
          />
          <PlayButtons displayAnswer={displayAnswer} setDisplayAnswer={setDisplayAnswer} />
        </div>
      </div>
    </>
  );
};
