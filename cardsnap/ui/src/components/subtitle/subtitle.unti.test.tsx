import { render, screen } from '@testing-library/react';
import { describe, it } from 'vitest';
import { Subtitle } from './subtitle';

describe('Subtitle unit test', () => {
  it('should display title prop', () => {
    const title = 'Unit Test';
    render(<Subtitle title={title} />);

    screen.getByText(title);
  });
});
