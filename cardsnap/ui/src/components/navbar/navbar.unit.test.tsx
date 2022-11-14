import { screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import { Navbar } from './navbar';
import { NavLinkType } from '../../helpers/navLinks';
import { renderWithRouter } from '../../helpers/testHelpers';

describe('Navbar unit test', () => {
  it('should render all navLinks', () => {
    const testNavLinks: NavLinkType[] = [{ label: 'Unit', url: 'Test' }];
    renderWithRouter(<Navbar navLinks={testNavLinks} />);

    const navItemList = screen.getByRole('list');

    expect(navItemList.children.length).toBe(testNavLinks.length);
  });
});
