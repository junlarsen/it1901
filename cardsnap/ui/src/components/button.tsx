import { FC } from 'react';

interface ButtonProps {
  clickHandler: () => void;
  label: string;
}

export const Button: FC<ButtonProps> = ({ clickHandler, label }) => (
  <button
    onClick={clickHandler}
    className="bg-sky-500 text-white py-2 w-1/4 shadow-md active:bg-sky-600 transition duration-100 ease-in-out"
  >
    {label}
  </button>
);
