import { FC } from 'react';
import { Link } from 'react-router-dom';
import { PlayView } from './playView';
import { Button } from '../../components/button/button';
import { Subtitle } from '../../components/subtitle/subtitle';
import { CardDeck } from '../../helpers/mockData';

interface DeckContainerProps {
  cardDeck: CardDeck;
}

/**
 * Displays the name of the deck and renders button to edit the deck and renders the playview.
 * @param cardDeck CardDeck to display
 */
export const DeckContainer: FC<DeckContainerProps> = ({ cardDeck }) => (
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
