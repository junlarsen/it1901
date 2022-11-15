import { render, screen, cleanup } from '@testing-library/react';
import { describe, it, afterEach } from 'vitest';
import { PenToggle } from './penToggle';

describe('PenToggle unit tests', () => {
  afterEach(() => {
    cleanup();
  });

  it('should render pen svg', () => {
    render(<PenToggle editToggle={false} clickHandler={() => null} />);

    screen.getByTestId('penSvg');
  });
});
