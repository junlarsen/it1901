import { FC } from 'react';
import { Button } from '../../../components/button';
import { Subtitle } from '../../../components/subtitle';

interface NormalViewProps {
  name: string;
  setEditToggle: React.Dispatch<React.SetStateAction<boolean>>;
}

export const NormalView: FC<NormalViewProps> = ({ name, setEditToggle }) => {
  const handleEditPress = () => setEditToggle(true);

  return (
    <div className="flex items-start h-8 gap-4">
      <Subtitle title={`Edit: ${name}`} />
      <Button clickHandler={handleEditPress} label="Edit name"></Button>
    </div>
  );
};
