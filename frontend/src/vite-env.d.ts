/// <reference types="vite/client" />

// (optional) add your own variables for autocomplete
interface ImportMetaEnv {
  readonly VITE_API_BASE: string
}
interface ImportMeta {
  readonly env: ImportMetaEnv
}
