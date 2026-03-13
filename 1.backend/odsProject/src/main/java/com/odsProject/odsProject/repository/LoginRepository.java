package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Roles;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sesiones;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.AuditoriaLogin;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.PermisosOds;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminAuditoriaLoginReciente;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminUsuariosActivos;
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
import static com.odsProject.odsProject.database.jooq.ods_login.tables.VistaAdminAuditoriaLoginReciente.VISTA_ADMIN_AUDITORIA_LOGIN_RECIENTE;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.VistaAdminResumenGeneral.VISTA_ADMIN_RESUMEN_GENERAL;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.VistaAdminUsuariosActivos.VISTA_ADMIN_USUARIOS_ACTIVOS;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.VistaAdminDetalleIndicadores.VISTA_ADMIN_DETALLE_INDICADORES;

/**
 * Implementación del Repositorio para el Sistema de Login
 * Implementa los métodos para acceder a los datos de autenticación, usuarios y sesión usando jOOQ
 * Usa datasource ods_login y sus stored procedures específicos
 */
@Repository
public class LoginRepository implements ILoginRepository {

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
        return dsl.insertInto(AUDITORIA_LOGIN)
                .set(dsl.newRecord(AUDITORIA_LOGIN, auditoriaLogin))
                .returning()
                .fetchOneInto(AuditoriaLogin.class);
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
    public List<VistaAdminAuditoriaLoginReciente> findVistaAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(VISTA_ADMIN_AUDITORIA_LOGIN_RECIENTE)
                .where(VISTA_ADMIN_AUDITORIA_LOGIN_RECIENTE.FECHA_EVENTO.ge(LocalDateTime.now().minusDays(dias)))
                .orderBy(VISTA_ADMIN_AUDITORIA_LOGIN_RECIENTE.FECHA_EVENTO.desc())
                .fetchInto(VistaAdminAuditoriaLoginReciente.class);
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
        if (proyectoId != null) {
            return dsl.selectFrom(VISTA_ADMIN_DETALLE_INDICADORES)
                    .where(VISTA_ADMIN_DETALLE_INDICADORES.PROYECTO_ID.eq(proyectoId))
                    .fetchInto(VistaAdminDetalleIndicadores.class);
        } else {
            return dsl.selectFrom(VISTA_ADMIN_DETALLE_INDICADORES)
                    .fetchInto(VistaAdminDetalleIndicadores.class);
        }
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
    public Boolean existsEmail(String email) {
        return dsl.selectCount()
                .from(USUARIOS)
                .where(USUARIOS.EMAIL.eq(email))
                .fetchOne(0, Integer.class) > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsUsername(String username) {
        return dsl.selectCount()
                .from(USUARIOS)
                .where(USUARIOS.USERNAME.eq(username))
                .fetchOne(0, Integer.class) > 0;
    }
}
