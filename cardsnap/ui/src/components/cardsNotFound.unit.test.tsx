import React from 'react';
import { screen, cleanup } from '@testing-library/react';
import { describe, it, afterEach } from 'vitest';
import { CardsNotFound } from './cardsNotFound';
import { renderWithRouter } from '../helpers/testHelpers';

describe('CardsNotFound unit tests', () => {
  afterEach(() => {
    cleanup();
  });

  it('should render prop id', () => {
    const id = 'unit test';
    renderWithRouter(<CardsNotFound id={id} />);

    screen.getByText(id);
  });

  it('should render error text when id is undefined', () => {
    const id = undefined;
    renderWithRouter(<CardsNotFound id={id} />);

    screen.getByText("Hmmmm couldn't read ID from URL");
  });
});
