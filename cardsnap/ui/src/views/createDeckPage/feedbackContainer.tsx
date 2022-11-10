import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../../components/button';
import { CardDeck } from '../../helpers/mockData';

interface FeecbackContainerProps {
  setDisplayFeedback: React.Dispatch<React.SetStateAction<boolean>>;
  feedbackText: string;
  isSucess: boolean;
  data: CardDeck | undefined;
}

export const FeecbackContainer: React.FC<FeecbackContainerProps> = ({
  setDisplayFeedback,
  feedbackText,
  isSucess,
  data,
}) => {
  const handleOkButtonPress = () => setDisplayFeedback(false);

  return (
    <div className="bg-white border-4 p-4 w-80 shadow-md mt-4">
      <p className="mb-4">{feedbackText}</p>
      {isSucess && data ? (
        <Link to={`edit/${data.id}`}>
          <Button label="Add cards" />
        </Link>
      ) : (
        <Button clickHandler={handleOkButtonPress} label="OK" type="gray" />
      )}
    </div>
  );
};
