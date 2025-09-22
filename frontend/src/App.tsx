import React, { useState } from "react";
import { RiskCard } from "./components/RiskCard";
import { submitVitals } from "./lib/api";

export default function App() {
  const [score, setScore] = useState(0);
  const [reasons, setReasons] = useState<string[]>([]);
  const [hr, setHr] = useState(110);
  const [sleep, setSleep] = useState(5.0);
  const [weight, setWeight] = useState(0);

  return (
    <main style={{ maxWidth: 720, margin: "40px auto", fontFamily: "system-ui, sans-serif" }}>
      <h1>ContinuumCare Pro</h1>
      <p>Send a sample vital to see risk scoring & explanations.</p>

      <form onSubmit={async (e) => {
        e.preventDefault();
        const base = import.meta.env.VITE_API_BASE || "http://localhost:8080";
        const resp = await submitVitals(base, {
          patientId: "demo",
          heartRate: Number(hr),
          sleepHours: Number(sleep),
          weightChange: Number(weight),
          tsMillis: Date.now()
        });
        setScore(resp.score); setReasons(resp.reasons);
      }}>
        <label>Heart Rate <input value={hr} onChange={e=>setHr(Number(e.target.value))} type="number"/></label>
        <label>Sleep Hours <input value={sleep} onChange={e=>setSleep(Number(e.target.value))} type="number" step="0.1"/></label>
        <label>Weight Change (lb) <input value={weight} onChange={e=>setWeight(Number(e.target.value))} type="number" step="0.1"/></label>
        <button type="submit">Score Risk</button>
      </form>

      <section style={{ marginTop: 24 }}>
        <RiskCard score={score} reasons={reasons}/>
      </section>
    </main>
  );
}
