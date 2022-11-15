import { FC } from 'react';
import { EditIcon } from './icons/edit';
import { TrashIcon } from './icons/trash';

interface ButtonProps {
  clickHandler?: () => void;
  label: string;
  type?: 'default' | 'delete' | 'edit' | 'gray' | 'red';
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

  if (type === 'edit') {
    return (
      <button onClick={clickHandler} className="bg-sky-500 p-2 rounded-full hover:bg-sky-600">
        <EditIcon />
      </button>
    );
  } else if (type === 'delete') {
    return (
      <button onClick={clickHandler} className="bg-red-500 p-2 rounded-full hover:bg-red-600">
        <TrashIcon />
      </button>
    );
  } else {
    return (
      <button
        onClick={clickHandler}
        className={`py-2 w-32 transition duration-100 ease-in-out rounded ${getColorClass()}`}
      >
        {label}
      </button>
    );
  }
};
