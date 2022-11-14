import { screen } from '@testing-library/react';
import { describe, it } from 'vitest';
import { CardDeckCard } from './cardDeckCard';
import { cardDecks } from '../../helpers/mockData';
import { renderWithRouter } from '../../helpers/testHelpers';

describe('CardDeckCard unit tests', () => {
  it('should render the name of the card', () => {
    const [cardDeck] = cardDecks;
    renderWithRouter(<CardDeckCard cardDeck={cardDeck} />);

    screen.getByText(cardDeck.name);
  });
});
