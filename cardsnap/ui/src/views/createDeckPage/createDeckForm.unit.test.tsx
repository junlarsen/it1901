import { render, screen } from '@testing-library/react';
import { describe, it, vi } from 'vitest';
import { CreateDeckForm } from './createDeckForm';

describe('CreateDeckForm unit tests', () => {
  it('should render helper text', () => {
    const mockMutate = vi.fn();
    const mockSetNewDeckName = vi.fn();
    render(<CreateDeckForm mutate={mockMutate} newDeckName="" setNewDeckName={mockSetNewDeckName} />);

    screen.getByText('Enter the name of the deck you want to create.');
  });
});
