import { render } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import { App } from './app';

describe('root app component', () => {
  it('should be able to render', () => {
    const component = render(<App />);
    expect(component.asFragment()).toMatchSnapshot();
  });
});
