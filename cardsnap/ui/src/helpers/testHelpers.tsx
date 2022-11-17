import React from 'react';
import { render, RenderResult } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';

/**
 * Helper for running tests.
 */
export const renderWithRouter = (component: React.ReactNode): RenderResult => {
  return {
    ...render(<MemoryRouter>{component}</MemoryRouter>),
  };
};
