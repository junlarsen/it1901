import { FC, useState } from 'react';
import { Button } from '../../components/button';

interface PlayButtonsProps {
  displayAnswer: boolean;
  setDisplayAnswer: React.Dispatch<React.SetStateAction<boolean>>;
  setDeckIndex: React.Dispatch<React.SetStateAction<number>>;
  deckSize: number;
  deckIndex: number;
}

export const PlayButtons: FC<PlayButtonsProps> = ({
  displayAnswer,
  setDisplayAnswer,
  setDeckIndex,
  deckSize,
  deckIndex,
}) => {
  const toggleDisplayAnswer = () => {
    setDisplayAnswer(!displayAnswer);
  };

  const nextCard = () => {
    setDisplayAnswer(false);
    paginateDeck(1);
  };

  const prevCard = () => {
    setDisplayAnswer(false);
    paginateDeck(-1);
  };

  const paginateDeck = (num: number) => {
    let newIndex = deckIndex + num;
    if (newIndex >= 0 && newIndex < deckSize) setDeckIndex(newIndex);
  };

  return (
    <div className="flex w-full mt-8 justify-between">
      <Button clickHandler={prevCard} label="Previous" />
      <Button clickHandler={toggleDisplayAnswer} label="Flip" />
      <Button clickHandler={nextCard} label="Next" />
    </div>
  );
};
