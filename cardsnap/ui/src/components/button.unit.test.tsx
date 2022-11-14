import { render, screen, cleanup } from '@testing-library/react';
import { describe, it, afterEach } from 'vitest';
import { Button } from './button';

describe('Button unit tests', () => {
  afterEach(() => {
    cleanup();
  });

  it('should render prop label', () => {
    const label = 'Button!';
    render(<Button label={label} />);

    screen.getByText(label);
  });

  it('should render with type selected', () => {
    const label = 'Button!';
    render(<Button label={label} type="red" />);

    screen.getByText(label);
  });

  it('should render edit svg', () => {
    const label = 'Button!';
    render(<Button label={label} type="edit" />);

    screen.getByTestId('editSvg');
  });
});
