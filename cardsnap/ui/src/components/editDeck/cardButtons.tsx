import { FC } from 'react';

export const CardButtons: FC = () => {
  const editCardHandler = () => {
    // Todo
  };

  const deleteCardHandler = () => {
    // Todo
  };

  return (
    <div className="top-4 absolute right-4 flex gap-4">
      <button onClick={editCardHandler} className="bg-sky-500 text-white w-32 py-2 hover:bg-sky-400 px-2 mt-2 mb-2">
        Edit
      </button>
      <button onClick={deleteCardHandler} className="bg-red-400 text-white w-32 py-2 hover:bg-red-200 px-2 mt-2 mb-2">
        Delete
      </button>
    </div>
  );
};
