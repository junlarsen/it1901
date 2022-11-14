import { render, screen } from '@testing-library/react';
import { describe, it } from 'vitest';
import { CardCard } from './CardCard';
import { Card, mockCard } from '../../../helpers/mockData';

describe('CardCard unit tests', () => {
  it('should show answer when toggled', () => {
    const card: Card = mockCard;
    render(<CardCard card={card} displayAnswer />);

    screen.getByText(card.answer);
  });
});
