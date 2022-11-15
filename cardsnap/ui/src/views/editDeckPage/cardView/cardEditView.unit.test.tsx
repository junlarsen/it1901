import React from 'react';
import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import { CardEditView } from './cardEditView';

describe('CardEditView unit tests', () => {
  it('should render two input fields', () => {
    render(<CardEditView updatedCard={{ answer: 'unit', question: 'test ' }} setUpdatedCard={() => null} />);

    const textInputs = screen.getAllByRole('textbox');

    expect(textInputs.length).toBe(2);
  });
});
