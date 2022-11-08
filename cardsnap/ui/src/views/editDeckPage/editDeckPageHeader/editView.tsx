import { FC, useState } from 'react';
import { Button } from '../../../components/button';

interface EditViewProps {
  name: string;
  setEditToggle: React.Dispatch<React.SetStateAction<boolean>>;
}

export const EditView: FC<EditViewProps> = ({ name, setEditToggle }) => {
  const [updatedName, setUpdatedName] = useState(name);

  const handleSave = () => {
    setEditToggle(false);
  };

  const handleCancelPress = () => {
    setEditToggle(false);
  };

  return (
    <div className="flex items-start h-8  gap-4">
      <input
        type="text"
        className="bg-gray-50 border-2 border-gray-300 text-gray-900 w-64 p-2"
        value={updatedName}
        onChange={(evt) => setUpdatedName(evt.target.value)}
      />
      <Button clickHandler={handleSave} label="Save" />
      <Button clickHandler={handleCancelPress} label="Cancel" type="gray" />
    </div>
  );
};
