export async function submitVitals(baseUrl: string, payload: any) {
  const res = await fetch(`${baseUrl}/api/v1/vitals`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json() as Promise<{ score: number; reasons: string[] }>;
}
