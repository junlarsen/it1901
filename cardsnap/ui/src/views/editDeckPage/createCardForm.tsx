import { FC, FormEventHandler } from 'react';

export const CreateCardForm: FC = () => {
  const onSubmit: FormEventHandler<HTMLFormElement> = (event) => {
    event.preventDefault();
  };

  return (
    <form onSubmit={onSubmit}>
      <input
        type="text"
        id="question"
        name="question"
        placeholder="Question"
        className="mt-4 bg-gray-50 border border-gray-300 text-gray-900 text-sm block p-2.5 w-full"
        required
      />
      <textarea
        rows={3}
        id="answer"
        name="answer"
        placeholder="Answer"
        className="mt-4 bg-gray-50 border border-gray-300 text-gray-900 text-sm block p-2.5 w-full"
        required
      />
      <input type="submit" value="Add card" className="text-white bg-sky-500 w-32 py-2 mt-4 cursor-pointer" />
    </form>
  );
};
