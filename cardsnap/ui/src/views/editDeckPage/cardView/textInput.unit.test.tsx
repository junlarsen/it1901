import { render, screen, cleanup, fireEvent } from '@testing-library/react';
import { describe, vi, expect, it, afterEach } from 'vitest';
import { TextInput } from './textInput';

describe('TextInput unit tests', () => {
  afterEach(() => {
    cleanup();
  });

  it('should render input with correct value', () => {
    const testValue = 'unit test';
    render(<TextInput value={testValue} handleChange={() => null} />);

    screen.getByDisplayValue(testValue);
  });

  it('should call handleChange function on change', () => {
    const testValue = 'unit test';
    const mock = vi.fn();
    render(<TextInput value={testValue} handleChange={mock} />);

    const input = screen.getByRole('textbox');

    fireEvent.input(input, { target: { value: 'updated text' } });

    expect(mock).toHaveBeenCalled();
  });
});
