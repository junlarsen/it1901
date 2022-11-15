import { render, screen } from '@testing-library/react';
import { describe, it, vi } from 'vitest';
import { CreateDeckForm } from './createDeckForm';

describe('CreateDeckForm unit tests', () => {
  it('should render helper text', () => {
    const mock = vi.fn();
    const mockSetNewDeckName = vi.fn();
    render(<CreateDeckForm handleCreateDeck={mock} newDeckName="" setNewDeckName={mockSetNewDeckName} />);

    screen.getByText('Enter the name of the deck you want to create.');
  });
});
