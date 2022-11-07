import { FC } from 'react';
import { Button } from '../../components/button';

export const CardButtonsContainer: FC = () => {
  const editCardHandler = () => {
    // Todo
  };

  const deleteCardHandler = () => {
    // Todo
  };

  return (
    <div className="top-4 absolute right-4 flex gap-4">
      <Button clickHandler={editCardHandler} label="Edit" />
      <Button clickHandler={deleteCardHandler} label="Delete" type="red" />
    </div>
  );
};
