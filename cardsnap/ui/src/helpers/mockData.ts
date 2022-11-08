export interface Card {
  question: string;
  answer: string;
}

export interface CardDeck {
  name: string;
  cards: Card[];
}

export interface TmpCardDeck {
  name: string;
  id: string;
}

export const cardDecks: CardDeck[] = [
  {
    name: 'Algdat',
    cards: [
      {
        question: "What's up?",
        answer: 'Not much',
      },
      {
        question: "What's down?",
        answer: 'The ground',
      },
    ],
  },
  {
    name: 'ITP',
    cards: [
      {
        question: "What's up!",
        answer: 'Not much',
      },
      {
        question: "What's down!",
        answer: 'The ground',
      },
    ],
  },
  {
    name: 'Endringsagent',
    cards: [
      {
        question: "What's up?",
        answer: 'Not much',
      },
      {
        question: "What's down?",
        answer: 'The ground',
      },
    ],
  },
  {
    name: 'DatDig',
    cards: [
      {
        question: "What's up!",
        answer: 'Not much',
      },
      {
        question: "What's down!",
        answer: 'The ground',
      },
    ],
  },
];
