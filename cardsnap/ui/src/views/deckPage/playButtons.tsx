import { FC } from 'react';
import { Button } from '../../components/button/button';

interface PlayButtonsProps {
  deckSize: number;
  displayAnswer: boolean;
  cardIndex: number;
  setDisplayAnswer: React.Dispatch<React.SetStateAction<boolean>>;
  setCardIndex: React.Dispatch<React.SetStateAction<number>>;
}

export const PlayButtons: FC<PlayButtonsProps> = ({
  deckSize,
  displayAnswer,
  cardIndex,
  setDisplayAnswer,
  setCardIndex,
}) => {
  const toggleDisplayAnswer = () => setDisplayAnswer(!displayAnswer);
  const handlePrevClick = () => handlePagination(-1);
  const handleNextClick = () => handlePagination(1);

  const handlePagination = (num: number) => {
    const updatedIndex = cardIndex + num;
    if (updatedIndex >= 0 && updatedIndex < deckSize) {
      setCardIndex(updatedIndex);
      setDisplayAnswer(false);
    }
  };

  return (
    <div className="flex w-full mt-8 justify-between">
      <Button clickHandler={handlePrevClick} label="Previous" type={cardIndex === 0 ? 'disabled' : 'default'} />
      <Button clickHandler={toggleDisplayAnswer} label="Flip" />
      <Button clickHandler={handleNextClick} label="Next" type={cardIndex === deckSize - 1 ? 'disabled' : 'default'} />
    </div>
  );
};
