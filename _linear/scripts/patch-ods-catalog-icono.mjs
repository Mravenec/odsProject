#!/usr/bin/env node
import fs from "fs";
import path from "path";
import { fileURLToPath } from "url";

const base = path.resolve(fileURLToPath(new URL("../../0.database/propuesta_actual", import.meta.url)));

for (const f of fs.readdirSync(base)) {
  const m = f.match(/ods(\d{2})_database/);
  if (!m) continue;
  const nn = m[1];
  const fp = path.join(base, f);
  let c = fs.readFileSync(fp, "utf8");
  if (c.includes("icono_url")) {
    console.log("SKIP (ya tiene icono_url)", f);
    continue;
  }
  const re =
    /INSERT IGNORE INTO ods_login\.ods_catalog \(id, nombre, color_hex, descripcion\)\s*\r?\nVALUES \(@ODS_NUM, ([^;]+)\);/;
  if (!re.test(c)) {
    console.log("SKIP (patrón no encontrado)", f);
    continue;
  }
  c = c.replace(
    re,
    `INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion, icono_url)\nVALUES (@ODS_NUM, $1, '/ods-icons/ods-${nn}.png');`
  );
  fs.writeFileSync(fp, c);
  console.log("OK", f);
}
