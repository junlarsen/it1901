import { FC } from 'react';
import { Link } from 'react-router-dom';

interface DeckButtonProps {
  cardDeckName: string;
  type: string;
}

export const DeckButton: FC<DeckButtonProps> = ({ cardDeckName, type }) => {
  const capitalizeFirst = (str: string) => {
    return str.charAt(0).toUpperCase() + str.slice(1);
  };

  return (
    <Link to={type + '/' + cardDeckName}>
      <button className="bg-sky-500 text-white w-32 py-2">{capitalizeFirst(type)}</button>
    </Link>
  );
};
