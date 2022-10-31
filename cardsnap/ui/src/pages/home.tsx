import { FC } from 'react';
import { CardDeckCard } from '../components/cardDeckCard';
import { Subtitle } from '../components/subtitle';
import { cardDecks } from '../helpers/mockData';

export const HomeView: FC = () => (
  <>
    <Subtitle title="Decks" />
    <div className="flex gap-4 max-w-2xl flex-wrap">
      {cardDecks.map((cardDeck) => (
        <CardDeckCard key={cardDeck.name} cardDeck={cardDeck} />
      ))}
    </div>
  </>
);
