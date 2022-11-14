import { FC } from 'react';

interface CardContentProps {
  type: 'Answer' | 'Question';
  text: string;
}

export const CardContent: FC<CardContentProps> = ({ type, text }) => (
  <div className={`${type === 'Question' ? 'front' : 'back'} absolute h-full w-full p-4 rounded`}>
    <p className="text-gray-600">{type}</p>
    <h2 className="text-xl my-4 font-medium">{text}</h2>
  </div>
);
