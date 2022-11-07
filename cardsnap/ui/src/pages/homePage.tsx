import { FC } from 'react';
import { CardDeckCard } from '../views/homePage/cardDeckCard';
import { Subtitle } from '../components/subtitle';
import { cardDecks } from '../helpers/mockData';

export const HomePage: FC = () => (
  <>
    <Subtitle title="Decks" />
    <div className="flex gap-4 max-w-2xl flex-wrap">
      {cardDecks.map((cardDeck) => (
        <CardDeckCard key={cardDeck.name} cardDeck={cardDeck} />
      ))}
    </div>
  </>
);
