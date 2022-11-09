export interface Card {
  question: string;
  answer: string;
}

export interface CardDeck {
  name: string;
  id: string;
}

export const cardDecks: CardDeck[] = [
  {
    name: 'Algdat',
    id: 'algdat',
  },
  {
    name: 'ITP',
    id: 'itp',
  },
  {
    name: 'Endringsagent',
    id: 'endringsagent',
  },
  {
    name: 'DatDig',
    id: 'datadig',
  },
];
