import { render, screen } from '@testing-library/react';
import { describe, it } from 'vitest';
import { IndexIndicator } from './indexIndicator';

describe('IndexIndicator unit tests', () => {
  it('should current index', () => {
    render(<IndexIndicator currentCount={1} totalCount={2} />);

    screen.getByText('1/2');
  });
});
