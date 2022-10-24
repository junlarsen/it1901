import { FC } from 'react';
import { Link, Outlet } from 'react-router-dom';

export const PageLayout: FC = () => {
  return (
    <div>
      <Link to="/home">Home</Link>
      <Link to="/decks/aabbcc">Deck aabbcc</Link>
      <Outlet />
    </div>
  );
};
