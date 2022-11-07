import { FC } from 'react';
import { Button } from '../../components/button';

interface PlayButtonsProps {
  displayAnswer: boolean;
  setDisplayAnswer: React.Dispatch<React.SetStateAction<boolean>>;
}

export const PlayButtons: FC<PlayButtonsProps> = ({ displayAnswer, setDisplayAnswer }) => {
  const toggleDisplayAnswer = () => {
    setDisplayAnswer(!displayAnswer);
  };

  const nextCard = () => {
    setDisplayAnswer(false);
  };

  const prevCard = () => {
    setDisplayAnswer(false);
  };

  return (
    <div className="flex w-full mt-8 justify-between">
      <Button clickHandler={prevCard} label="Previous" />
      <Button clickHandler={toggleDisplayAnswer} label="Flip" />
      <Button clickHandler={nextCard} label="Next" />
    </div>
  );
};
