import { fireEvent, render, screen, cleanup } from '@testing-library/react';
import { describe, it, expect, vi, afterEach } from 'vitest';
import { FeedbackContainer } from './feedbackContainer';
import { cardDecks } from '../../helpers/mockData';
import { renderWithRouter } from '../../helpers/testHelpers';

describe('FeedbackContainer unit tests', () => {
  afterEach(() => {
    cleanup();
  });

  it('should show success title when data is defined', () => {
    const setDisplayFeedback = () => null;
    const feedbackText = 'Unit test';
    const isSuccess = true;
    const data = cardDecks[0];
    renderWithRouter(
      <FeedbackContainer
        setDisplayFeedback={setDisplayFeedback}
        feedbackText={feedbackText}
        validity={isSuccess}
        data={data}
      />,
    );

    screen.getByText('Success!');
  });

  it('should show error title when data is undefined', () => {
    const setDisplayFeedback = () => null;
    const feedbackText = 'Unit test';
    const isSuccess = false;
    render(
      <FeedbackContainer
        setDisplayFeedback={setDisplayFeedback}
        feedbackText={feedbackText}
        validity={isSuccess}
        data={undefined}
      />,
    );

    screen.getByText('Error');
  });

  it('should call setDisplayFeedback when OK is pressed', () => {
    const mock = vi.fn();
    const feedbackText = 'Unit test';
    const isSuccess = true;
    const data = cardDecks[0];
    renderWithRouter(
      <FeedbackContainer setDisplayFeedback={mock} feedbackText={feedbackText} validity={isSuccess} data={data} />,
    );

    fireEvent.click(screen.getByText('OK'));

    expect(mock).toHaveBeenCalled();
  });
});
