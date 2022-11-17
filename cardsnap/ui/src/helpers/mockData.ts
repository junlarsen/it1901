/**
 * Defines the types of data from the API with.
 * Coresponding to the java-objects and the fields in backend.
 */

export interface Card {
  question: string;
  answer: string;
  id: string;
  owner: string;
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

export const mockCard: Card = { question: 'unit', answer: 'test', id: '1', owner: 'itp' };
