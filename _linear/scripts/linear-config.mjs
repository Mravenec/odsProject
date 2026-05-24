import { envFileLoaded, envFilePath } from "../load-env.mjs";
import { LinearClient } from "@linear/sdk";

const LINEAR_API_KEY = process.env.LINEAR_API_KEY?.trim();
const keyOk =
  LINEAR_API_KEY &&
  /^lin_api_[A-Za-z0-9]+$/.test(LINEAR_API_KEY) &&
  LINEAR_API_KEY.length >= 40 &&
  !/REEMPLAZA|xxxxxxxx|tu-key|nueva/i.test(LINEAR_API_KEY);

if (!keyOk) {
  console.error("❌ LINEAR_API_KEY inválida o no definida.");
  console.error(`   1. copy .env.example .env   (desde la carpeta _linear)`);
  console.error(`   2. Edita .env y pega tu Personal API key (empieza con lin_api_)`);
  console.error(`   3. Si usaste $env:LINEAR_API_KEY antes: Remove-Item Env:LINEAR_API_KEY -ErrorAction SilentlyContinue`);
  console.error(`   Archivo: ${envFilePath}${envFileLoaded ? " ✓" : " — no existe"}`);
  if (LINEAR_API_KEY) console.error(`   Valor actual en entorno: ${LINEAR_API_KEY.slice(0, 12)}… (rechazado)`);
  console.error("   Linear → Settings → API → Personal API keys");
  process.exit(1);
}

export const LINEAR_TEAM_NAME = process.env.LINEAR_TEAM_NAME || "linear_ods";
export const linear = new LinearClient({ apiKey: LINEAR_API_KEY });
