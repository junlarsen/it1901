import { FC } from 'react';

interface TextInputProps {
  value: string;
  handleChange: (evt: React.ChangeEvent<HTMLInputElement>) => void;
  size?: 'long' | 'short';
}

export const TextInput: FC<TextInputProps> = ({ value, handleChange, size = 'short' }) => (
  <input
    type="text"
    className={`w-1/2 ${
      size === 'short' ? 'md:w-1/3' : ''
    } border-b-2 border-blue-500 py-2 focus:ring-0 focus:outline-none`}
    value={value}
    onChange={handleChange}
  />
);
