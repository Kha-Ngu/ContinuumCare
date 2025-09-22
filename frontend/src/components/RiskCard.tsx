import React from "react";

export function RiskCard({ score, reasons }: { score: number; reasons: string[] }) {
  const level = score >= 5 ? "High" : score >= 2 ? "Medium" : "Low";
  return (
    <div style={{ padding: 16, borderRadius: 12, boxShadow: "0 4px 12px rgba(0,0,0,0.1)" }}>
      <h3>Risk: <span aria-label="risk-level">{level}</span></h3>
      {reasons.length > 0 && (
        <details>
          <summary>Details</summary>
          <ul>{reasons.map(r => <li key={r}>{r}</li>)}</ul>
        </details>
      )}
    </div>
  );
}
