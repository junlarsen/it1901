import { FC } from 'react';
import { PenIcon } from '../icons/penIcon';

interface PenToggleProps {
  editToggle: boolean;
  clickHandler: () => void;
}

/**
 * Renders pen icons displayed in edit deck.
 * @param editToggle boolean for styling if active
 * @param clickHandler function to be called whenn clicked
 */
export const PenToggle: FC<PenToggleProps> = ({ editToggle, clickHandler }) => (
  <button
    onClick={clickHandler}
    className={`${editToggle ? 'bg-blue-200' : 'hover:bg-blue-100'} p-2 rounded-full`}
    data-testid="card-edit-toggle"
  >
    <PenIcon />
  </button>
);
