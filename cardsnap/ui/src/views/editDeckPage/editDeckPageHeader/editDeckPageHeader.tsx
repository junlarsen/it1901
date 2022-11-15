import { RefetchOptions, RefetchQueryFilters, QueryObserverResult } from '@tanstack/react-query';
import { FC, useState } from 'react';
import { EditView } from './editView';
import { NormalView } from './normalView';
import { CardDeck } from '../../../helpers/mockData';

interface EditDeckPageHeaderProps {
  deck: CardDeck;
  refetch: <TPageData>(
    options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined,
  ) => Promise<QueryObserverResult<CardDeck>>;
}

export const EditDeckPageHeader: FC<EditDeckPageHeaderProps> = ({ deck, refetch }) => {
  const [editToggle, setEditToggle] = useState(false);

  return (
    <div className="mb-8 bg-white shadow p-4 rounded">
      {editToggle ? (
        <EditView deck={deck} setEditToggle={setEditToggle} refetch={refetch} />
      ) : (
        <NormalView deck={deck} setEditToggle={setEditToggle} />
      )}
    </div>
  );
};
