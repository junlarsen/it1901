import { FC } from 'react';
import { Button } from '../../components/button';
import { Card } from '../../helpers/mockData';

interface CardButtonsContainerProps {
  card: Card;
}

export const CardButtonsContainer: FC<CardButtonsContainerProps> = ({ card }) => {
  const editCardHandler = () => {
    // TODO edit card using card.id
  };

  const deleteCardHandler = () => {
    // TODO delete card using card.id
  };

  return (
    <div className="top-4 absolute right-4 flex gap-4">
      <Button clickHandler={editCardHandler} label="Edit" />
      <Button clickHandler={deleteCardHandler} label="Delete" type="red" />
    </div>
  );
};
