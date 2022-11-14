import { FC } from 'react';
import { Link } from 'react-router-dom';
import { PlayView } from './playView';
import { Button } from '../../components/button';
import { Subtitle } from '../../components/subtitle';
import { CardDeck } from '../../helpers/mockData';

interface DeckContainerProps {
  cardDeck: CardDeck;
}

export const DeckContainer: FC<DeckContainerProps> = ({ cardDeck }) => {
  return (
    <>
      <div className="flex gap-4">
        <Subtitle title={cardDeck.name} />
        <div>
          <Link to="edit">
            <Button label="Edit" type="edit" />
          </Link>
        </div>
      </div>
      <PlayView id={cardDeck.id} />
    </>
  );
};
