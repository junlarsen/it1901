import { screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import { Footer } from './footer';
import { renderWithRouter } from '../../helpers/testHelpers';

describe('Footer unit tests', () => {
  it('should render three links', () => {
    renderWithRouter(<Footer />);

    const linkList = screen.getByRole('list');

    expect(linkList.children.length).toBe(4);
  });
});
