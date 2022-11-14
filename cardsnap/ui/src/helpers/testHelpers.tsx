import React from 'react';
import { render, RenderResult } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';

export const renderWithRouter = (component: React.ReactNode): RenderResult => {
  return {
    ...render(<MemoryRouter>{component}</MemoryRouter>),
  };
};
