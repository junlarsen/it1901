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

// Successfully ensures user-story 5, 8, and 9
test('create and edit and delete card in deck', async ({ page, browserName }) => {
  const name = getNameForBrowser(browserName);
  // Open the deck
  await page.getByTestId(`link-${name}`).click();
  await expect(page).toHaveURL(PAGE_DECK_REGEXP);
  // Click the edit icon button
  await page.getByTestId('button-edit').click();
  await expect(page).toHaveURL(PAGE_DECK_EDIT_REGEXP);
  // Create a new card
  await page.getByPlaceholder('Question').click();
  await page.getByPlaceholder('Question').fill('My Q');
  await page.getByPlaceholder('Answer').click();
  await page.getByPlaceholder('Answer').fill('My A');
  await page.getByRole('button', { name: 'Add card' }).click();
  // Go edit that card now
  await page.getByTestId('card-edit-toggle').click();
  await page.locator('input[type="text"]').nth(1).fill('Foo');
  await page.locator('input[type="text"]').nth(2).fill('Bar');
  await page.getByRole('button', { name: 'Save' }).click();
  // And now delete the card
  await page.getByTestId('card-edit-toggle').click();
  await page.getByTestId('button-delete').click();
});
