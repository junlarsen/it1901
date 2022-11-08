import { FC } from 'react';

interface ButtonProps {
  clickHandler: () => void;
  label: string;
  type?: 'default' | 'gray' | 'red';
}

export const Button: FC<ButtonProps> = ({ clickHandler, label, type = 'default' }) => {
  const getColorClass = () => {
    if (type === 'red') {
      return 'bg-red-400 active:bg-red-500 border-2 border-red-400 active:border-red-500';
    }
    if (type === 'gray') {
      return 'bg-none text-gray-500 border-gray-400 border-2';
    }
    return 'bg-sky-500 active:bg-sky-600 border-2 border-sky-500 active:border-sky-600';
  };

  return (
    <button
      onClick={clickHandler}
      className={`text-white py-2 w-32 transition duration-100 ease-in-out ${getColorClass()}`}
    >
      {label}
    </button>
  );
};
