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
