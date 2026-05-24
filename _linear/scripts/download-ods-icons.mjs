#!/usr/bin/env node
/**
 * Descarga íconos del grid ods.cr (item01.png … item17.png)
 * Fuente: /themes/custom/ods10/img/ — mismos que #obj-item1 … #obj-item17
 */
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const OUT = path.resolve(
  fileURLToPath(new URL("../../2.frontend/odsProject/public/ods-icons", import.meta.url))
);
fs.mkdirSync(OUT, { recursive: true });

const BASE = "https://ods.cr/themes/custom/ods10/img";

for (let i = 1; i <= 17; i++) {
  const nn = String(i).padStart(2, "0");
  const url = `${BASE}/item${nn}.png`;
  const dest = path.join(OUT, `ods-${nn}.png`);
  const res = await fetch(url);
  if (!res.ok) throw new Error(`${res.status} ${url}`);
  const buf = Buffer.from(await res.arrayBuffer());
  fs.writeFileSync(dest, buf);
  console.log(`OK ods-${nn}.png (${buf.length} bytes) ← ${url}`);
}
