import { test, expect } from '@playwright/test';
import {
  getNameForBrowser,
  deleteSampleDeck,
  createSampleDeck,
  PAGE_DECK_REGEXP,
  PAGE_DECK_EDIT_REGEXP,
} from './util.js';

const getEditedName = (browserName: string): string => {
  return `My edited deck name for ${browserName}`;
};

test.beforeEach(createSampleDeck());
test.afterEach(deleteSampleDeck(getEditedName));

// Successfully ensures user-story 4
test('edit existing deck name', async ({ page, browserName }) => {
  const name = getNameForBrowser(browserName);
  const editedName = getEditedName(browserName);
  // Open the deck
  await page.getByTestId(`link-${name}`).click();
  await expect(page).toHaveURL(PAGE_DECK_REGEXP);
  // Click the edit icon button
  await page.getByTestId('button-edit').click();
  await expect(page).toHaveURL(PAGE_DECK_EDIT_REGEXP);
  // Click the edit name button and fill in the input
  await page.getByRole('button', { name: 'Edit name' }).click();
  await page.locator('input[type="text"]').first().click(); //
  await page.locator('input[type="text"]').first().fill(editedName);
  await page.getByRole('button', { name: 'Save' }).click();
  // The name should be changed when going home now
  await page.getByText('Home').nth(0).click();
  await expect(page.getByText(editedName)).toBeVisible();
});
