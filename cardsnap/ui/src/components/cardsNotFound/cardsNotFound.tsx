import { FC } from 'react';
import { Link } from 'react-router-dom';
import { Subtitle } from '../subtitle/subtitle';

interface DeckNotFoundProps {
  id: string | undefined;
}

/**
 * Component to render if error occurs when getting cards in a deck.
 * Text displayed depends on the id, if its undefined or has value.
 * @param id string og undefined, the id of the deck
 */
export const CardsNotFound: FC<DeckNotFoundProps> = ({ id }) => (
  <>
    <Subtitle title="Oops! Something went wrong" />
    {id === undefined ? (
      <p>Hmmmm couldn't read ID from URL</p>
    ) : (
      <p>
        Couldn't get gards from deck with id: <span className="italic">{id}</span>
      </p>
    )}
    <p>
      Please go{' '}
      <Link to="/" className="text-blue-500 hover:pointer">
        Home
      </Link>{' '}
      to find your decks
    </p>
  </>
);
