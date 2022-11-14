import { FC } from 'react';
import { Button } from '../../components/button';

export const DeleteDeckButton: FC = () => {
  const deleteDeckHandler = () => {
    // TODO
  };

  return (
    <div className="mt-8">
      <Button clickHandler={deleteDeckHandler} label="Delete Deck" type="red" />
    </div>
  );
};
