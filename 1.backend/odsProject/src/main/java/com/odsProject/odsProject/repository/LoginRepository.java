package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Roles;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sesiones;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.AuditoriaLogin;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.PermisosOds;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminUsuariosActivos;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sedes;
import com.odsProject.odsProject.database.jooq.ods_login.routines.SpAdminUsuarios;
import com.odsProject.odsProject.database.jooq.ods_login.routines.SpLogin;
import com.odsProject.odsProject.database.jooq.ods_login.routines.SpLogout;
import com.odsProject.odsProject.repository.interfaces.ILoginRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods_login.tables.Usuarios.USUARIOS;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.Roles.ROLES;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.Sesiones.SESIONES;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.AuditoriaLogin.AUDITORIA_LOGIN;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.PermisosOds.PERMISOS_ODS;
import static com.odsProject.odsProject.database.jooq.ods01.tables.VistaAdminResumenGeneral.VISTA_ADMIN_RESUMEN_GENERAL;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.VistaAdminUsuariosActivos.VISTA_ADMIN_USUARIOS_ACTIVOS;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.Sedes.SEDES;

/**
 * Implementación del Repositorio para el Sistema de Login
 * Implementa los métodos para acceder a los datos de autenticación, usuarios y sesión usando jOOQ
 * Usa datasource ods_login y sus stored procedures específicos
 */
@Repository
public class LoginRepository implements ILoginRepository {

    /**
     * Contexto DSL para la base de datos 'ods_login'
     */
    @Autowired
    @Qualifier("dslOdsLogin")
    private DSLContext dsl;

    // ── Usuarios ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> findUsuarioById(Integer id) {
        return dsl.selectFrom(USUARIOS)
                .where(USUARIOS.ID.eq(id))
                .fetchOptionalInto(Usuarios.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> findUsuarioByUsername(String username) {
        return dsl.selectFrom(USUARIOS)
                .where(USUARIOS.USERNAME.eq(username))
                .fetchOptionalInto(Usuarios.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> findUsuarioByEmail(String email) {
        return dsl.selectFrom(USUARIOS)
                .where(USUARIOS.EMAIL.eq(email))
                .fetchOptionalInto(Usuarios.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuarios> findUsuariosActivos() {
        return dsl.selectFrom(USUARIOS)
                .where(USUARIOS.IS_ACTIVE.eq((byte) 1))
                .fetchInto(Usuarios.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios saveUsuario(Usuarios usuario) {
        return dsl.insertInto(USUARIOS)
                .set(dsl.newRecord(USUARIOS, usuario))
                .returning()
                .fetchOneInto(Usuarios.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios updateUsuario(Usuarios usuario) {
        var update = dsl.update(USUARIOS)
                .set(USUARIOS.USERNAME, usuario.getUsername())
                .set(USUARIOS.EMAIL, usuario.getEmail())
                .set(USUARIOS.FULL_NAME, usuario.getFullName())
                .set(USUARIOS.ROL_ID, usuario.getRolId())
                .set(USUARIOS.SEDE_ID, usuario.getSedeId());

        if (usuario.getIsActive() != null) {
            update.set(USUARIOS.IS_ACTIVE, usuario.getIsActive());
        }
        if (usuario.getEmailVerificado() != null) {
            update.set(USUARIOS.EMAIL_VERIFICADO, usuario.getEmailVerificado());
        }
        if (usuario.getPasswordHash() != null) {
            update.set(USUARIOS.PASSWORD_HASH, usuario.getPasswordHash());
        }
        if (usuario.getAreaId() != null) {
            update.set(USUARIOS.AREA_ID, usuario.getAreaId());
        }
        if (usuario.getDependenciaId() != null) {
            update.set(USUARIOS.DEPENDENCIA_ID, usuario.getDependenciaId());
        }
        if (usuario.getRolDependenciaId() != null) {
            update.set(USUARIOS.ROL_DEPENDENCIA_ID, usuario.getRolDependenciaId());
        }
        if (usuario.getUnidadProgramaticaId() != null) {
            update.set(USUARIOS.UNIDAD_PROGRAMATICA_ID, usuario.getUnidadProgramaticaId());
        }
        if (usuario.getTelefonoContacto() != null) {
            update.set(USUARIOS.TELEFONO_CONTACTO, usuario.getTelefonoContacto());
        }

        update.where(USUARIOS.ID.eq(usuario.getId())).execute();
        return findUsuarioById(usuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactivateUsuario(Integer id) {
        dsl.update(USUARIOS)
                .set(USUARIOS.IS_ACTIVE, (byte) 0)
                .where(USUARIOS.ID.eq(id))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> findAllUsuariosAdmin() {
        return dsl.select(
                        USUARIOS.ID.as("id"),
                        USUARIOS.USERNAME.as("username"),
                        USUARIOS.FULL_NAME.as("fullName"),
                        USUARIOS.EMAIL.as("email"),
                        USUARIOS.ROL_ID.as("rolId"),
                        ROLES.NOMBRE.as("rol"),
                        USUARIOS.SEDE_ID.as("sedeId"),
                        SEDES.NOMBRE.as("sede"),
                        USUARIOS.AREA_ID.as("areaId"),
                        USUARIOS.DEPENDENCIA_ID.as("dependenciaId"),
                        USUARIOS.ROL_DEPENDENCIA_ID.as("rolDependenciaId"),
                        USUARIOS.UNIDAD_PROGRAMATICA_ID.as("unidadProgramaticaId"),
                        USUARIOS.TELEFONO_CONTACTO.as("telefonoContacto"),
                        USUARIOS.IS_ACTIVE.as("isActive"),
                        USUARIOS.ULTIMO_LOGIN.as("ultimoLogin"),
                        USUARIOS.CREATED_AT.as("createdAt"))
                .from(USUARIOS)
                .leftJoin(ROLES).on(USUARIOS.ROL_ID.eq(ROLES.ID))
                .leftJoin(SEDES).on(USUARIOS.SEDE_ID.eq(SEDES.ID))
                .orderBy(USUARIOS.ID.asc())
                .fetchMaps();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer countActiveAdmins() {
        return dsl.selectCount()
                .from(USUARIOS)
                .join(ROLES).on(USUARIOS.ROL_ID.eq(ROLES.ID))
                .where(ROLES.NOMBRE.eq("admin"))
                .and(USUARIOS.IS_ACTIVE.eq((byte) 1))
                .fetchOne(0, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUltimoLogin(Integer usuarioId) {
        dsl.update(USUARIOS)
                .set(USUARIOS.ULTIMO_LOGIN, LocalDateTime.now())
                .where(USUARIOS.ID.eq(usuarioId))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementarIntentosFallidos(Integer usuarioId) {
        dsl.update(USUARIOS)
                .set(USUARIOS.INTENTOS_FALLIDOS, org.jooq.impl.DSL.val(USUARIOS.INTENTOS_FALLIDOS.add(1), org.jooq.impl.SQLDataType.TINYINTUNSIGNED))
                .where(USUARIOS.ID.eq(usuarioId))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bloquearUsuario(Integer usuarioId, LocalDateTime hasta) {
        dsl.update(USUARIOS)
                .set(USUARIOS.BLOQUEADO_HASTA, hasta)
                .where(USUARIOS.ID.eq(usuarioId))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void desbloquearUsuario(Integer usuarioId) {
        dsl.update(USUARIOS)
                .set(USUARIOS.BLOQUEADO_HASTA, (LocalDateTime) null)
                .set(USUARIOS.INTENTOS_FALLIDOS, org.jooq.impl.DSL.val(0, org.jooq.impl.SQLDataType.TINYINTUNSIGNED))
                .where(USUARIOS.ID.eq(usuarioId))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarTokenRecuperacion(Integer usuarioId, String token, LocalDateTime expira) {
        dsl.update(USUARIOS)
                .set(USUARIOS.TOKEN_RECUPERACION, token)
                .set(USUARIOS.TOKEN_EXPIRA, expira)
                .where(USUARIOS.ID.eq(usuarioId))
                .execute();
    }

    // ── Roles ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Roles> findAllRoles() {
        return dsl.selectFrom(ROLES)
                .fetchInto(Roles.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Roles> findRolById(Integer id) {
        return dsl.selectFrom(ROLES)
                .where(ROLES.ID.eq(id))
                .fetchOptionalInto(Roles.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Roles> findRolByNombre(String nombre) {
        return dsl.selectFrom(ROLES)
                .where(ROLES.NOMBRE.eq(nombre))
                .fetchOptionalInto(Roles.class);
    }

    // ── Sedes ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sedes> findAllSedes() {
        return dsl.selectFrom(SEDES)
                .fetchInto(Sedes.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Sedes> findSedeById(Integer id) {
        return dsl.selectFrom(SEDES)
                .where(SEDES.ID.eq(id))
                .fetchOptionalInto(Sedes.class);
    }

    // ── Sesiones ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Sesiones> findSesionByToken(String tokenHash) {
        return dsl.selectFrom(SESIONES)
                .where(SESIONES.TOKEN_HASH.eq(tokenHash))
                .and(SESIONES.REVOCADA.eq((byte) 0))
                .and(SESIONES.EXPIRA_EN.gt(LocalDateTime.now()))
                .fetchOptionalInto(Sesiones.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sesiones saveSesion(Sesiones sesion) {
        return dsl.insertInto(SESIONES)
                .set(dsl.newRecord(SESIONES, sesion))
                .returning()
                .fetchOneInto(Sesiones.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void revocarSesion(String tokenHash) {
        dsl.update(SESIONES)
                .set(SESIONES.REVOCADA, (byte) 1)
                .where(SESIONES.TOKEN_HASH.eq(tokenHash))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void revocarSesionesByUsuario(Integer usuarioId) {
        dsl.update(SESIONES)
                .set(SESIONES.REVOCADA, (byte) 1)
                .where(SESIONES.USUARIO_ID.eq(usuarioId))
                .and(SESIONES.REVOCADA.eq((byte) 0))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer limpiarSesionesExpiradas() {
        return dsl.deleteFrom(SESIONES)
                .where(SESIONES.EXPIRA_EN.lt(LocalDateTime.now()))
                .or(SESIONES.REVOCADA.eq((byte) 1))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sesiones> findSesionesByUsuario(Integer usuarioId) {
        return dsl.selectFrom(SESIONES)
                .where(SESIONES.USUARIO_ID.eq(usuarioId))
                .and(SESIONES.REVOCADA.eq((byte) 0))
                .and(SESIONES.EXPIRA_EN.gt(LocalDateTime.now()))
                .fetchInto(Sesiones.class);
    }

    // ── Permisos ODS ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PermisosOds> findPermisosByUsuario(Integer usuarioId) {
        return dsl.selectFrom(PERMISOS_ODS)
                .where(PERMISOS_ODS.USUARIO_ID.eq(usuarioId))
                .and(PERMISOS_ODS.PUEDE_VER.eq((byte) 1))
                .fetchInto(PermisosOds.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasPermisoOds(Integer usuarioId, Integer odsId) {
        return dsl.selectCount()
                .from(PERMISOS_ODS)
                .where(PERMISOS_ODS.USUARIO_ID.eq(usuarioId))
                .and(PERMISOS_ODS.ODS_NUM.eq(org.jooq.impl.DSL.val(odsId, org.jooq.impl.SQLDataType.TINYINTUNSIGNED)))
                .and(PERMISOS_ODS.PUEDE_VER.eq((byte) 1))
                .fetchOne(0, Integer.class) > 0;
    }

    // ── Auditoría Login ──

    /**
     * {@inheritDoc}
     */
    @Override
    public AuditoriaLogin saveAuditoriaLogin(AuditoriaLogin auditoriaLogin) {
        try {
            return dsl.insertInto(AUDITORIA_LOGIN)
                    .set(dsl.newRecord(AUDITORIA_LOGIN, auditoriaLogin))
                    .returning()
                    .fetchOneInto(AuditoriaLogin.class);
        } catch (Exception returningFailed) {
            // MariaDB a veces no soporta RETURNING de forma fiable con JOOQ
            int rows = dsl.insertInto(AUDITORIA_LOGIN)
                    .set(dsl.newRecord(AUDITORIA_LOGIN, auditoriaLogin))
                    .execute();
            if (rows < 1) {
                throw new IllegalStateException("No se insertó auditoria_login", returningFailed);
            }
            return auditoriaLogin;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaLogin> findAuditoriaByUsuario(Integer usuarioId, Integer dias) {
        return dsl.selectFrom(AUDITORIA_LOGIN)
                .where(AUDITORIA_LOGIN.USUARIO_ID.eq(usuarioId))
                .and(AUDITORIA_LOGIN.FECHA_EVENTO.ge(LocalDateTime.now().minusDays(dias)))
                .orderBy(AUDITORIA_LOGIN.FECHA_EVENTO.desc())
                .fetchInto(AuditoriaLogin.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaLogin> findIntentosFallidosRecientes(Integer horas) {
        return dsl.selectFrom(AUDITORIA_LOGIN)
                .where(AUDITORIA_LOGIN.EVENTO.eq(com.odsProject.odsProject.database.jooq.ods_login.enums.AuditoriaLoginEvento.LOGIN_FALLIDO))
                .and(AUDITORIA_LOGIN.FECHA_EVENTO.ge(LocalDateTime.now().minusHours(horas)))
                .orderBy(AUDITORIA_LOGIN.FECHA_EVENTO.desc())
                .fetchInto(AuditoriaLogin.class);
    }

    // ─── Vistas Administrativas ───

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> findVistaAuditoriaReciente(Integer dias) {
        int d = (dias == null || dias < 1) ? 30 : Math.min(dias, 365);
        // Tabla directa (no la vista con WHERE fijo 30d): el admin ve el rango pedido.
        return dsl.select(
                        AUDITORIA_LOGIN.ID,
                        AUDITORIA_LOGIN.FECHA_EVENTO,
                        AUDITORIA_LOGIN.EVENTO,
                        USUARIOS.USERNAME,
                        USUARIOS.FULL_NAME,
                        ROLES.NOMBRE,
                        AUDITORIA_LOGIN.EMAIL_INTENTO,
                        AUDITORIA_LOGIN.IP_ADDRESS,
                        AUDITORIA_LOGIN.USER_AGENT,
                        AUDITORIA_LOGIN.DETALLE)
                .from(AUDITORIA_LOGIN)
                .leftJoin(USUARIOS).on(AUDITORIA_LOGIN.USUARIO_ID.eq(USUARIOS.ID))
                .leftJoin(ROLES).on(USUARIOS.ROL_ID.eq(ROLES.ID))
                .where(AUDITORIA_LOGIN.FECHA_EVENTO.ge(LocalDateTime.now().minusDays(d)))
                .orderBy(AUDITORIA_LOGIN.FECHA_EVENTO.desc())
                .limit(500)
                .fetch(r -> {
                    Map<String, Object> map = new java.util.LinkedHashMap<>();
                    map.put("id", r.get(AUDITORIA_LOGIN.ID));
                    map.put("fechaEvento", r.get(AUDITORIA_LOGIN.FECHA_EVENTO));
                    var ev = r.get(AUDITORIA_LOGIN.EVENTO);
                    map.put("evento", ev != null ? ev.getLiteral() : null);
                    map.put("username", r.get(USUARIOS.USERNAME));
                    map.put("fullName", r.get(USUARIOS.FULL_NAME));
                    map.put("rol", r.get(ROLES.NOMBRE));
                    map.put("emailIntento", r.get(AUDITORIA_LOGIN.EMAIL_INTENTO));
                    map.put("ipAddress", r.get(AUDITORIA_LOGIN.IP_ADDRESS));
                    map.put("userAgent", r.get(AUDITORIA_LOGIN.USER_AGENT));
                    map.put("detalle", r.get(AUDITORIA_LOGIN.DETALLE));
                    return map;
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VistaAdminResumenGeneral> findVistaResumenGeneral() {
        return dsl.selectFrom(VISTA_ADMIN_RESUMEN_GENERAL)
                .fetchInto(VistaAdminResumenGeneral.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VistaAdminUsuariosActivos> findVistaUsuariosActivos() {
        return dsl.selectFrom(VISTA_ADMIN_USUARIOS_ACTIVOS)
                .orderBy(VISTA_ADMIN_USUARIOS_ACTIVOS.ULTIMO_LOGIN.desc())
                .fetchInto(VistaAdminUsuariosActivos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VistaAdminDetalleIndicadores> findVistaDetalleIndicadores(Integer proyectoId) {
        if (proyectoId == null) {
            return aggregateVistaDetalleIndicadores(null);
        }
        return aggregateVistaDetalleIndicadores(proyectoId);
    }

    /** Indicadores viven en ods01..ods17; no solo en ods01. */
    private List<VistaAdminDetalleIndicadores> aggregateVistaDetalleIndicadores(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> out = new java.util.ArrayList<>();
        for (int i = 1; i <= 17; i++) {
            String schema = String.format("ods%02d", i);
            try {
                var view = org.jooq.impl.DSL.table(org.jooq.impl.DSL.name(schema, "vista_admin_detalle_indicadores"));
                var proyectoField = org.jooq.impl.DSL.field(
                        org.jooq.impl.DSL.name(schema, "vista_admin_detalle_indicadores", "proyecto_id"),
                        Integer.class);
                if (proyectoId != null) {
                    out.addAll(dsl.selectFrom(view)
                            .where(proyectoField.eq(proyectoId))
                            .fetchInto(VistaAdminDetalleIndicadores.class));
                } else {
                    out.addAll(dsl.selectFrom(view)
                            .fetchInto(VistaAdminDetalleIndicadores.class));
                }
            } catch (Exception ignore) {
                // schema no disponible
            }
        }
        return out;
    }

    // ─── Stored Procedures ───

    /**
     * {@inheritDoc}
     */
    @Override
    public SpLogin executeSpLogin(String email, String passwordHash, String ip, String userAgent) {
        SpLogin sp = new SpLogin();
        sp.setPEmail(email);
        sp.setPPasswordHash(passwordHash);
        sp.setPIp(ip);
        sp.setPUserAgent(userAgent);
        sp.execute(dsl.configuration());
        return sp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpLogout executeSpLogout(String tokenHash) {
        SpLogout sp = new SpLogout();
        sp.setPTokenHash(tokenHash);
        sp.setPIp("127.0.0.1"); // IP por defecto
        sp.execute(dsl.configuration());
        return sp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> executeSpAdminUsuarios(String accion, Integer usuarioId, String username, String email, Integer rolId) {
        SpAdminUsuarios sp = new SpAdminUsuarios();
        // El stored procedure no tiene parámetros definidos, ejecutar directamente
        sp.execute(dsl.configuration());
        
        return Map.of(
            "status", "executed",
            "accion", accion,
            "usuarioId", usuarioId,
            "resultado", "SpAdminUsuarios executed"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getEstadisticasSistema() {
        // Obtener estadísticas básicas del sistema
        Integer totalUsuarios = dsl.selectCount().from(USUARIOS).fetchOne(0, Integer.class);
        Integer usuariosActivos = dsl.selectCount().from(USUARIOS).where(USUARIOS.IS_ACTIVE.eq((byte) 1)).fetchOne(0, Integer.class);
        Integer sesionesActivas = dsl.selectCount().from(SESIONES)
                .where(SESIONES.REVOCADA.eq((byte) 0))
                .and(SESIONES.EXPIRA_EN.gt(LocalDateTime.now()))
                .fetchOne(0, Integer.class);
        Integer intentosFallidosHoy = dsl.selectCount().from(AUDITORIA_LOGIN)
                .where(AUDITORIA_LOGIN.EVENTO.eq(com.odsProject.odsProject.database.jooq.ods_login.enums.AuditoriaLoginEvento.LOGIN_FALLIDO))
                .and(AUDITORIA_LOGIN.FECHA_EVENTO.ge(LocalDateTime.now().toLocalDate().atStartOfDay()))
                .fetchOne(0, Integer.class);
        
        return Map.of(
            "totalUsuarios", totalUsuarios,
            "usuariosActivos", usuariosActivos,
            "sesionesActivas", sesionesActivas,
            "intentosFallidosHoy", intentosFallidosHoy,
            "fechaConsulta", LocalDateTime.now()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsEmail(String email, Integer excludeId) {
        var condition = USUARIOS.EMAIL.eq(email);
        if (excludeId != null) {
            condition = condition.and(USUARIOS.ID.ne(excludeId));
        }
        return dsl.selectCount()
                .from(USUARIOS)
                .where(condition)
                .fetchOne(0, Integer.class) > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsUsername(String username, Integer excludeId) {
        var condition = USUARIOS.USERNAME.eq(username);
        if (excludeId != null) {
            condition = condition.and(USUARIOS.ID.ne(excludeId));
        }
        return dsl.selectCount()
                .from(USUARIOS)
                .where(condition)
                .fetchOne(0, Integer.class) > 0;
    }
}
