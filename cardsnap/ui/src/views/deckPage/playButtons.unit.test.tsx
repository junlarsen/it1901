import React from 'react';
import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import { PlayButtons } from './playButtons';

describe('PlayButtons unit tests', () => {
  it('should show three buttons', () => {
    render(
      <PlayButtons
        displayAnswer={false}
        setDisplayAnswer={() => null}
        setCardIndex={() => null}
        deckSize={1}
        cardIndex={0}
      />,
    );

    const buttons = screen.getAllByRole('button');

    expect(buttons.length).toBe(3);
  });
});
