import { expect, type PlaywrightTestArgs, type PlaywrightWorkerOptions } from '@playwright/test';

/**
 * Helper function to create a unique name for a deck to be created by
 * Playwright.
 *
 * Because Playwright will run Chromium, Firefox Stable, and WebKit browsers
 * all in parallel name collisions would mess up the tests. Therefore, we
 * create a unique name for the card based on the browser name.
 */
export const getNameForBrowser = (browserName: BrowserName): string => {
  return `My epic ${browserName} playwright deck`;
};

// Regular expressions to match different pages
export const PAGE_HOME_REGEXP = /localhost:3000\//;
export const PAGE_CREATE_REGEXP = /localhost:3000\/create/;
export const PAGE_ABOUT_REGEXP = /localhost:3000\/about/;
export const PAGE_DECK_REGEXP = /localhost:3000\/deck\/.+/;
export const PAGE_DECK_EDIT_REGEXP = /localhost:3000\/deck\/.+\/edit/;

export type BrowserName = PlaywrightWorkerOptions['browserName'];

/**
 * Utility function to create the dummy deck to be used for this test.
 *
 * This is to avoid repeating this code in each test setup.
 */
export const createSampleDeck =
  (generator: typeof getNameForBrowser = getNameForBrowser) =>
  async ({ page, browserName }: PlaywrightTestArgs & PlaywrightWorkerOptions): Promise<void> => {
    const name = generator(browserName);
    await page.goto('http://localhost:3000');

    // Get the create button
    await page.getByText('Create').nth(0).click();
    await expect(page).toHaveURL('http://localhost:3000/create');
    // Fill in some text in the input, find it by the placeholder text
    await page.getByPlaceholder('AlgDat').click();
    await page.getByPlaceholder('AlgDat').fill(name);
    // Click the button, and the OK button afterwards
    await page.getByRole('button', { name: 'Create' }).click();
    await page.getByRole('button', { name: 'OK' }).click();
    // Go back home
    await page.getByText('Home').nth(0).click();
  };

/**
 * Utility function to delete the deck created by createSampleDeck
 *
 * This is to avoid repeating this code in each test teardown.
 */
export const deleteSampleDeck =
  (generator: typeof getNameForBrowser = getNameForBrowser) =>
  async ({ page, browserName }: PlaywrightTestArgs & PlaywrightWorkerOptions): Promise<void> => {
    const name = generator(browserName);
    // Go back home
    await page.getByText('Home').nth(0).click();
    // Click our new card
    await page.getByText(new RegExp(name)).click();
    // Click the edit button, then delete the deck with confirmation
    await page.getByTestId('button-edit').click();
    await page.getByRole('button', { name: 'Delete Deck' }).click();
    await page.getByRole('button', { name: 'Yes' }).click();
  };
