import { test, expect } from '@playwright/test';
import {
  getNameForBrowser,
  deleteSampleDeck,
  createSampleDeck,
  PAGE_DECK_REGEXP,
  PAGE_DECK_EDIT_REGEXP,
} from './util.js';

test.beforeEach(createSampleDeck());
test.afterEach(deleteSampleDeck());

// Successfully ensures user-story 6
test('create edit and delete card', async ({ page, browserName }) => {
  const name = getNameForBrowser(browserName);
  // Open the deck
  await page.getByTestId(`link-${name}`).click();
  await expect(page).toHaveURL(PAGE_DECK_REGEXP);
  // Click the edit icon button
  await page.getByTestId('button-edit').click();
  await expect(page).toHaveURL(PAGE_DECK_EDIT_REGEXP);
  // Create two new cards
  await page.getByPlaceholder('Question').click();
  await page.getByPlaceholder('Question').fill('My Q');
  await page.getByPlaceholder('Answer').click();
  await page.getByPlaceholder('Answer').fill('My A');
  await page.getByRole('button', { name: 'Add card' }).click();
  // Create the second card
  await page.getByPlaceholder('Question').click();
  await page.getByPlaceholder('Question').fill('Second Q');
  await page.getByPlaceholder('Answer').click();
  await page.getByPlaceholder('Answer').fill('Second A');
  await page.getByRole('button', { name: 'Add card' }).click();
  // Go home and play that deck
  await page.getByText('Home').nth(0).click();
  await expect(page.getByText(name)).toBeVisible();
  await page.getByTestId(`link-${name}`).click();
  await expect(page).toHaveURL(PAGE_DECK_REGEXP);
  // Check that the first card is showing
  await expect(page.getByText('Question')).toBeVisible();
  // Flip the card
  await page.getByText('Flip').click();
  // Check that the answer now shows
  await expect(page.getByText('Answer')).toBeVisible();
  await page.getByText('Next').click();
  // We can now go back to the first card
  await page.getByText('Previous').click();
  await expect(page.getByText('Question')).toBeVisible();
});
