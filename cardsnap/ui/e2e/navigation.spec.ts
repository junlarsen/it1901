import { test, expect } from '@playwright/test';
import { PAGE_ABOUT_REGEXP, PAGE_HOME_REGEXP } from './util.js';

test.beforeEach(async ({ page }) => {
  await page.goto('http://localhost:3000');
});

// Successfully ensures user-story 1 (in some way, ensures the app loads)
test('root application navigation', async ({ page }) => {
  const about = page.getByText('About').nth(0);
  await expect(about).toHaveAttribute('href', '/about');
  // Clicking the page should now redirect us and we should find the text
  await about.click();
  await expect(page.getByText('About🌸')).toBeVisible();
  // Clicking back to the home page
  const home = page.getByText('Home').nth(0);
  await expect(home).toHaveAttribute('href', '/');
  await expect(page).toHaveURL(PAGE_ABOUT_REGEXP);
  await home.click();
  await expect(page.getByText('Home🏡')).toBeVisible();
  await expect(page).toHaveURL(PAGE_HOME_REGEXP);
});
