import { render, screen } from '@testing-library/react';
import { describe, it } from 'vitest';
import { CardNormalView } from './cardNormalView';
import { mockCard } from '../../../helpers/mockData';

describe('CardNormalView unit tests', () => {
  it('should render question and answer from card prop', () => {
    render(<CardNormalView card={mockCard} />);

    screen.getByText(mockCard.question);
    screen.getByText(mockCard.answer);
  });
});
