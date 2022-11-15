import { FC } from 'react';
import { PenIcon } from './icons/pen';

interface PenToggleProps {
  editToggle: boolean;
  clickHandler: () => void;
}

export const PenToggle: FC<PenToggleProps> = ({ editToggle, clickHandler }) => (
  <button onClick={clickHandler} className={`${editToggle ? 'bg-blue-200' : 'hover:bg-blue-100'} p-2 rounded-full`}>
    <PenIcon />
  </button>
);
