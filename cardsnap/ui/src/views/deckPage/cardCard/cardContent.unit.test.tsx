import { render, screen } from '@testing-library/react';
import { describe, it } from 'vitest';
import { CardContent } from './cardContent';

describe('CardContent unit tests', () => {
  it('should render type and text', () => {
    const type = 'Question';
    const text = 'Unit test';
    render(<CardContent type={type} text={text} />);

    screen.getByText(type);
    screen.getByText(text);
  });
});
