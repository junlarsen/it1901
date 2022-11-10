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
    <div className="bg-white rounded p-4 max-w-sm shadow-md mt-4">
      <h2 className={`text-xl ${isSucess ? 'text-blue-600' : 'text-red-600'}`}>{isSucess ? 'Success!' : 'Error'}</h2>
      <p className="mb-4">{feedbackText}</p>
      <div className="flex gap-2">
        {isSucess && data && (
          <Link to={`edit/${data.id}`}>
            <Button label="Add cards" />
          </Link>
        )}
        <Button clickHandler={handleOkButtonPress} label="OK" type="gray" />
      </div>
    </div>
  );
};
