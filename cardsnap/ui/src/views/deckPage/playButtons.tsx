import { FC } from 'react';
import { Button } from '../../components/button/button';

interface PlayButtonsProps {
  deckSize: number;
  displayAnswer: boolean;
  cardIndex: number;
  setDisplayAnswer: React.Dispatch<React.SetStateAction<boolean>>;
  setCardIndex: React.Dispatch<React.SetStateAction<number>>;
}

/**
 * Renders the buttons for userinteraction when playing a deck
 * @param deckSize number the size of the deck
 * @param displayAnswer boolean show/hide the answer
 * @param cardIndex number current index
 * @param setDisplayAnswer SetStateAction<boolean> to set hide/show of answer
 * @param setCardIndex SetStateAction<number> to set index in deck (navigate through deck)
 * @returns
 */
export const PlayButtons: FC<PlayButtonsProps> = ({
  deckSize,
  displayAnswer,
  cardIndex,
  setDisplayAnswer,
  setCardIndex,
}) => {
  /**
   * Clickhandlers
   */
  const toggleDisplayAnswer = () => setDisplayAnswer(!displayAnswer);
  const handlePrevClick = () => handlePagination(-1);
  const handleNextClick = () => handlePagination(1);

  /**
   * Handles pagination in deck.
   * @param num number distance to nest card to be shown. (negative value goes back to previous card)
   */
  const handlePagination = (num: number) => {
    const updatedIndex = cardIndex + num;
    if (updatedIndex >= 0 && updatedIndex < deckSize) {
      setCardIndex(updatedIndex);
      setDisplayAnswer(false);
    }
  };

  /**
   * Renders a div with the three buttons for user interaction
   */
  return (
    <div className="flex w-full mt-8 justify-between">
      <Button clickHandler={handlePrevClick} label="Previous" type={cardIndex === 0 ? 'disabled' : 'default'} />
      <Button clickHandler={toggleDisplayAnswer} label="Flip" />
      <Button clickHandler={handleNextClick} label="Next" type={cardIndex === deckSize - 1 ? 'disabled' : 'default'} />
    </div>
  );
};
