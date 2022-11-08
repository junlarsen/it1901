import { FC, useState } from 'react';
import { EditView } from './editView';
import { NormalView } from './normalView';

interface EditDeckPageHeaderProps {
  name: string;
}

export const EditDeckPageHeader: FC<EditDeckPageHeaderProps> = ({ name }) => {
  const [editToggle, setEditToggle] = useState(false);

  return (
    <div className="mb-8 h-8">
      {editToggle ? (
        <EditView name={name} setEditToggle={setEditToggle} />
      ) : (
        <NormalView name={name} setEditToggle={setEditToggle} />
      )}
    </div>
  );
};
