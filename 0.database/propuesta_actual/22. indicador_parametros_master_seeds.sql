USE ods_login;

-- Seeds for indicador_parametros_master
-- For each indicator with formula (p1/p2)*100, insert p1 and p2
-- ODS 01
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Número de personas en situación de pobreza extrema','Decimal' FROM indicador_master WHERE codigo='1.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total del área de medición','Decimal' FROM indicador_master WHERE codigo='1.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas bajo la línea de pobreza nacional','Decimal' FROM indicador_master WHERE codigo='1.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total del área','Decimal' FROM indicador_master WHERE codigo='1.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con protección social mínima','Decimal' FROM indicador_master WHERE codigo='1.3.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total','Decimal' FROM indicador_master WHERE codigo='1.3.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con acceso a servicios básicos','Decimal' FROM indicador_master WHERE codigo='1.4.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total','Decimal' FROM indicador_master WHERE codigo='1.4.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con derechos seguros sobre la tierra','Decimal' FROM indicador_master WHERE codigo='1.4.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población con acceso a tierra','Decimal' FROM indicador_master WHERE codigo='1.4.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas afectadas por desastres','Decimal' FROM indicador_master WHERE codigo='1.5.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Cada 100,000 habitantes de referencia','Decimal' FROM indicador_master WHERE codigo='1.5.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Pérdidas económicas por desastres (USD)','Decimal' FROM indicador_master WHERE codigo='1.5.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','PIB total (USD)','Decimal' FROM indicador_master WHERE codigo='1.5.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Gasto público en servicios básicos (USD)','Decimal' FROM indicador_master WHERE codigo='1.a.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Presupuesto público total (USD)','Decimal' FROM indicador_master WHERE codigo='1.a.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Gasto social del gobierno (USD)','Decimal' FROM indicador_master WHERE codigo='1.a.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','PIB total (USD)','Decimal' FROM indicador_master WHERE codigo='1.a.2';
-- ODS 02
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas subalimentadas','Decimal' FROM indicador_master WHERE codigo='2.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total','Decimal' FROM indicador_master WHERE codigo='2.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con inseguridad alimentaria','Decimal' FROM indicador_master WHERE codigo='2.1.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total','Decimal' FROM indicador_master WHERE codigo='2.1.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Niños menores de 5 años con retraso del crecimiento','Decimal' FROM indicador_master WHERE codigo='2.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de niños menores de 5 años','Decimal' FROM indicador_master WHERE codigo='2.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Niños con malnutrición (emaciación o sobrepeso)','Decimal' FROM indicador_master WHERE codigo='2.2.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de niños menores de 5 años','Decimal' FROM indicador_master WHERE codigo='2.2.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Mujeres 15-49 años con anemia','Decimal' FROM indicador_master WHERE codigo='2.2.3';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de mujeres 15-49 años','Decimal' FROM indicador_master WHERE codigo='2.2.3';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Superficie agrícola sostenible (ha)','Decimal' FROM indicador_master WHERE codigo='2.4.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Superficie agrícola total (ha)','Decimal' FROM indicador_master WHERE codigo='2.4.1';
-- ODS 03
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Muertes maternas registradas','Decimal' FROM indicador_master WHERE codigo='3.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Cada 100,000 nacidos vivos','Decimal' FROM indicador_master WHERE codigo='3.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Partos atendidos por personal calificado','Decimal' FROM indicador_master WHERE codigo='3.1.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de partos registrados','Decimal' FROM indicador_master WHERE codigo='3.1.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Muertes de niños menores de 5 años','Decimal' FROM indicador_master WHERE codigo='3.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Cada 1,000 nacidos vivos','Decimal' FROM indicador_master WHERE codigo='3.2.1';
-- ODS 04
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Niños que completan educación primaria/secundaria','Decimal' FROM indicador_master WHERE codigo='4.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de niños en edad escolar','Decimal' FROM indicador_master WHERE codigo='4.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Niños con acceso a educación preescolar de calidad','Decimal' FROM indicador_master WHERE codigo='4.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de niños en edad preescolar','Decimal' FROM indicador_master WHERE codigo='4.2.1';
-- ODS 05
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Mujeres que han sufrido violencia de pareja','Decimal' FROM indicador_master WHERE codigo='5.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de mujeres encuestadas','Decimal' FROM indicador_master WHERE codigo='5.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Mujeres en parlamentos o asambleas','Decimal' FROM indicador_master WHERE codigo='5.5.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de escaños en parlamentos','Decimal' FROM indicador_master WHERE codigo='5.5.1';
-- ODS 06
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con acceso a agua potable segura','Decimal' FROM indicador_master WHERE codigo='6.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total del área','Decimal' FROM indicador_master WHERE codigo='6.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con acceso a saneamiento gestionado','Decimal' FROM indicador_master WHERE codigo='6.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total del área','Decimal' FROM indicador_master WHERE codigo='6.2.1';
-- ODS 07
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas con acceso a electricidad','Decimal' FROM indicador_master WHERE codigo='7.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total','Decimal' FROM indicador_master WHERE codigo='7.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Energía renovable generada (MWh)','Decimal' FROM indicador_master WHERE codigo='7.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Energía total generada (MWh)','Decimal' FROM indicador_master WHERE codigo='7.2.1';
-- ODS 08
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Jóvenes desempleados (15-24 años)','Decimal' FROM indicador_master WHERE codigo='8.6.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de jóvenes en edad laboral','Decimal' FROM indicador_master WHERE codigo='8.6.1';
-- ODS 09
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Valor añadido en manufactura (USD)','Decimal' FROM indicador_master WHERE codigo='9.2.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','PIB total (USD)','Decimal' FROM indicador_master WHERE codigo='9.2.1';
-- ODS 10
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Ingreso del 40% más pobre de la población','Decimal' FROM indicador_master WHERE codigo='10.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Ingreso total de la población','Decimal' FROM indicador_master WHERE codigo='10.1.1';
-- ODS 11
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas en barrios marginales (tugurios)','Decimal' FROM indicador_master WHERE codigo='11.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población urbana total','Decimal' FROM indicador_master WHERE codigo='11.1.1';
-- ODS 12
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Residuos municipales sólidos generados (ton)','Decimal' FROM indicador_master WHERE codigo='12.5.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de residuos generados (ton)','Decimal' FROM indicador_master WHERE codigo='12.5.1';
-- ODS 13
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Personas afectadas por eventos climáticos extremos','Decimal' FROM indicador_master WHERE codigo='13.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Población total expuesta','Decimal' FROM indicador_master WHERE codigo='13.1.1';
-- ODS 14
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Área marina protegida (km²)','Decimal' FROM indicador_master WHERE codigo='14.5.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Área marina total jurisdicción (km²)','Decimal' FROM indicador_master WHERE codigo='14.5.1';
-- ODS 15
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Área forestal con gestión sostenible (ha)','Decimal' FROM indicador_master WHERE codigo='15.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Superficie forestal total (ha)','Decimal' FROM indicador_master WHERE codigo='15.1.1';
-- ODS 16
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Homicidios intencionales registrados','Decimal' FROM indicador_master WHERE codigo='16.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Cada 100,000 habitantes','Decimal' FROM indicador_master WHERE codigo='16.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Niños (5-17 años) en trabajo infantil','Decimal' FROM indicador_master WHERE codigo='16.2.2';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','Total de niños 5-17 años','Decimal' FROM indicador_master WHERE codigo='16.2.2';
-- ODS 17
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p1','Ingresos fiscales del gobierno (USD)','Decimal' FROM indicador_master WHERE codigo='17.1.1';
INSERT IGNORE INTO indicador_parametros_master (indicador_id, nombre_parametro, descripcion_param, tipo_dato) SELECT id,'p2','PIB total (USD)','Decimal' FROM indicador_master WHERE codigo='17.1.1';

SELECT CONCAT('indicador_parametros_master: ', COUNT(*), ' registros sembrados') AS resultado FROM indicador_parametros_master;
