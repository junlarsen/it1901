import { FC } from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../../../components/button/button';
import { Subtitle } from '../../../components/subtitle/subtitle';
import { CardDeck } from '../../../helpers/mockData';

interface NormalViewProps {
  deck: CardDeck;
  setEditToggle: React.Dispatch<React.SetStateAction<boolean>>;
}

export const NormalView: FC<NormalViewProps> = ({ deck, setEditToggle }) => {
  const handleEditPress = () => setEditToggle(true);

  return (
    <div className="flex items-start h-10 gap-4">
      <Subtitle title={deck.name} />
      <Button clickHandler={handleEditPress} label="Edit name" />
      <Link to={`/deck/${deck.id}`}>
        <Button label="Play" />
      </Link>
    </div>
  );
};
