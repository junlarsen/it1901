import { FC } from 'react';
import { Button } from '../../components/button';

interface EditNameContainerProps {
  name: string;
}

export const EditNameContainer: FC<EditNameContainerProps> = ({ name }) => {
  const handleSave = () => {
    // TODO
  };

  return (
    <div className={`flex items-start h-8 gap-4 `}>
      <input type="text" placeholder={name} className="bg-gray-50 border border-gray-300 text-gray-900 w-64 p-2.5" />
      <Button clickHandler={handleSave} label="Save" />
      <Button clickHandler={() => console.log('...')} label="Cancel" type="gray" />
    </div>
  );
};
