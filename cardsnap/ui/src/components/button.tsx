import { FC } from 'react';

interface ButtonProps {
  clickHandler?: () => void;
  label: string;
  type?: 'default' | 'gray' | 'red';
}

export const Button: FC<ButtonProps> = ({ clickHandler = () => null, label, type = 'default' }) => {
  const getColorClass = () => {
    if (type === 'red') {
      return 'bg-red-400 hover:bg-red-500 text-white active:bg-red-500 border border-red-400 active:border-red-500';
    }
    if (type === 'gray') {
      return 'bg-none hover:bg-gray-100 text-gray-500 border-gray-800 border active:bg-gray-50';
    }
    return 'bg-sky-500 hover:bg-sky-600 text-white active:bg-sky-600 border border-sky-500 active:border-sky-600';
  };

  return (
    <button onClick={clickHandler} className={`py-2 w-32 transition duration-100 ease-in-out ${getColorClass()}`}>
      {label}
    </button>
  );
};
