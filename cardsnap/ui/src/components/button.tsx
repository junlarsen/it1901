import { FC } from 'react';

interface ButtonProps {
  clickHandler: () => void;
  label: string;
  type?: 'default' | 'red';
}

export const Button: FC<ButtonProps> = ({ clickHandler, label, type = 'default' }) => {
  const getColorClass = () => {
    if (type === 'red') {
      return 'bg-red-400 active:bg-red-500';
    }
    return 'bg-sky-500 active:bg-sky-600';
  };

  return (
    <button
      onClick={clickHandler}
      className={`text-white py-2 w-32 shadow-md transition duration-100 ease-in-out ${getColorClass()}`}
    >
      {label}
    </button>
  );
};
