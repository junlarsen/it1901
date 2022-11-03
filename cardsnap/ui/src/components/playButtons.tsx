import { FC } from 'react';

export const PlayButtons: FC = () => {
  const flipCard = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
  };

  const nextCard = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
  };

  const prevCard = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
  };

  const style = 'bg-sky-500 text-white py-2 w-1/4 shadow-md';

  return (
    <div className="flex w-full mt-8 justify-between">
      <button onClick={prevCard} className={style} name="prevButton">
        Previous
      </button>
      <button onClick={flipCard} className={style} name="flipButton">
        Flip card
      </button>
      <button onClick={nextCard} className={style} name="nextButton">
        Next
      </button>
    </div>
  );
};
