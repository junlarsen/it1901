import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../../components/button/button';
import { CardDeck } from '../../helpers/mockData';

interface FeedbackContainerProps {
  setDisplayFeedback: React.Dispatch<React.SetStateAction<boolean>>;
  feedbackText: string;
  validity: boolean;
  data: CardDeck | undefined;
}

export const FeedbackContainer: React.FC<FeedbackContainerProps> = ({
  setDisplayFeedback,
  feedbackText,
  validity,
  data,
}) => {
  const handleOkButtonPress = () => setDisplayFeedback(false);

  return (
    <div className="bg-white rounded p-4 max-w-sm shadow-md mt-4">
      <h2 className={`text-xl ${validity ? 'text-blue-600' : 'text-red-600'}`}>{validity ? 'Success!' : 'Error'}</h2>
      <p className="mb-4">{feedbackText}</p>
      <div className="flex gap-2">
        {validity && data && (
          <Link to={`/deck/${data.id}/edit`}>
            <Button label="Add cards" />
          </Link>
        )}

        <Button clickHandler={handleOkButtonPress} label="OK" type="gray" />
      </div>
    </div>
  );
};
