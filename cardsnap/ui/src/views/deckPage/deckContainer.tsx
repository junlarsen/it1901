import { FC } from 'react';
import { Subtitle } from '../../components/subtitle';
import { CardDeck } from '../../helpers/mockData';

interface DeckContainerProps {
  cardDeck: CardDeck;
}

export const DeckContainer: FC<DeckContainerProps> = ({ cardDeck }) => (
  <>
    <Subtitle title={cardDeck.name} />
    <p>You should be able to both play and edit this deck😧</p>
  </>
);
