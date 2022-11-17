import { FC } from 'react';
import { Link } from 'react-router-dom';
import { Subtitle } from '../subtitle/subtitle';

interface DeckNotFoundProps {
  id: string | undefined;
}

/**
 * Component to render if error occurs when getting a deck.
 * Text displayed depends on the id, if its undefined or has value.
 * @param id string or undefined, the deckid
 */
export const DeckNotFound: FC<DeckNotFoundProps> = ({ id }) => (
  <>
    <Subtitle title="Oops! Something went wrong" />
    {id === undefined ? (
      <p>Hmmmm couldn't read ID from URL</p>
    ) : (
      <p>
        Deck with id: <span className="italic">{id}</span> is not found
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
