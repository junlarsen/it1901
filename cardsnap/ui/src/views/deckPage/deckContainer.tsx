import { FC } from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../../components/button';
import { Subtitle } from '../../components/subtitle';
import { CardDeck } from '../../helpers/mockData';
import { PlayView } from './playView';

interface DeckContainerProps {
  cardDeck: CardDeck;
}

export const DeckContainer: FC<DeckContainerProps> = ({ cardDeck }) => {
  return (
    <>
      <Subtitle title={cardDeck.name} />
      <Link to="edit">
        <Button label="Edit" />
      </Link>
      <PlayView id={cardDeck.id} />
    </>
  );
};
