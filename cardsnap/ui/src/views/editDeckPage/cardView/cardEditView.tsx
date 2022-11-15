import { FC } from 'react';
import { TextInput } from './textInput';

interface CardEditViewProps {
  updatedCard: {
    question: string;
    answer: string;
  };
  setUpdatedCard: React.Dispatch<
    React.SetStateAction<{
      question: string;
      answer: string;
    }>
  >;
}

export const CardEditView: FC<CardEditViewProps> = ({ updatedCard, setUpdatedCard }) => (
  <div className="flex flex-col md:flex-row gap-4 h-24 md:items-center">
    <TextInput
      value={updatedCard.question}
      handleChange={(evt) => setUpdatedCard({ ...updatedCard, question: evt.target.value })}
    />
    <TextInput
      value={updatedCard.answer}
      handleChange={(evt) => setUpdatedCard({ ...updatedCard, answer: evt.target.value })}
      size="long"
    />
  </div>
);
