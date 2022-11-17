import { test, expect } from '@playwright/test';
import { PAGE_CREATE_REGEXP, PAGE_DECK_EDIT_REGEXP, PAGE_DECK_REGEXP, PAGE_HOME_REGEXP } from './util.js';

test.beforeEach(async ({ page }) => {
  await page.goto('http://localhost:3000');
});

// Successfully ensures user-story 2, 3, and 7
// Note: does not use beforeEach/afterEach setup, since this actually ensures
// the creation/deletion functionality works.
test('create new deck and delete it', async ({ page, browserName }) => {
  const uniqueDeckName = `My epic ${browserName} playwright deck`;
  // Get the create button
  await page.getByText('Create').nth(0).click();
  await expect(page).toHaveURL(PAGE_CREATE_REGEXP);
  // Fill in some text in the input, find it by the placeholder text
  await page.getByPlaceholder('AlgDat').click();
  await page.getByPlaceholder('AlgDat').fill(uniqueDeckName);
  // Click the button, and the OK button afterwards
  await page.getByRole('button', { name: 'Create' }).click();
  await page.getByRole('button', { name: 'OK' }).click();
  // Go back home
  await page.getByText('Home').nth(0).click();
  await expect(page).toHaveURL(PAGE_HOME_REGEXP);
  // Click our new card
  await page.getByText(new RegExp(uniqueDeckName)).click();
  await expect(page.getByText(uniqueDeckName)).toBeVisible();
  await expect(page).toHaveURL(PAGE_DECK_REGEXP);
  // Click the edit button, then delete the deck with confirmation
  await page.getByRole('button').click();
  await expect(page).toHaveURL(PAGE_DECK_EDIT_REGEXP);
  await page.getByRole('button', { name: 'Delete Deck' }).click();
  await page.getByRole('button', { name: 'Yes' }).click();
  // Expect to be redirected back home
  await expect(page).toHaveURL(PAGE_HOME_REGEXP);
});
