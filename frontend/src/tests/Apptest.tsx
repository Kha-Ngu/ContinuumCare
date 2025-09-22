import { render, screen } from "@testing-library/react";
import App from "../App";
import { toBeInTheDocument } from '@testing-library/jest-dom';

expect.extend({ toBeInTheDocument });

it("shows a risk card with an explanation", () => {
  render(<App />);
  expect(screen.getByText(/ContinuumCare Pro/i)).toBeInTheDocument();
});
