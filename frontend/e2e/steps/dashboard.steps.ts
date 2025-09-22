import { Given, When, Then } from "@cucumber/cucumber";
import { expect } from "@playwright/test";
import { getPage } from "../world"; // small helper returning the Playwright page

Given("the app is open", async () => {
  const page = await getPage();
  await page.goto(process.env.BASE_URL || "http://localhost:5173");
});

When("I view the patient list", async () => { /* optionally click nav */ });

Then("I see a {string} risk badge", async (label: string) => {
  const page = await getPage();
  await expect(page.getByText(label)).toBeVisible();
});

Then("I can expand to view {string}", async (reason: string) => {
  const page = await getPage();
  await page.getByRole("button", { name: /details/i }).click();
  await expect(page.getByText(reason)).toBeVisible();
});
