package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods_master.Tables.PROYECTOS;

/**
 * Implementación del Repositorio Maestro de Proyectos
 * Centraliza la gestión de proyectos en la base de datos 'ods_master'
 */
@Repository
public class MasterProjectRepository implements IMasterProjectRepository {

    @Autowired
    @Qualifier("dslOdsMaster")
    private DSLContext dsl;

    @Override
    public List<Proyectos> findAll() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }

    @Override
    public Optional<Proyectos> findById(Integer id) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(id))
                .fetchOptionalInto(Proyectos.class);
    }

    @Override
    public List<Proyectos> findByUsuario(Integer usuarioId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.USUARIO_ID.eq(usuarioId))
                .fetchInto(Proyectos.class);
    }

    @Override
    public List<Proyectos> findBySede(Integer sedeId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.SEDE_ID.eq(sedeId))
                .fetchInto(Proyectos.class);
    }

    @Override
    public List<Proyectos> findByEstado(String estado) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ESTADO.cast(String.class).eq(estado))
                .fetchInto(Proyectos.class);
    }

    @Override
    public Proyectos save(Proyectos proyecto) {
        return dsl.insertInto(PROYECTOS)
                .set(dsl.newRecord(PROYECTOS, proyecto))
                .returning()
                .fetchOneInto(Proyectos.class);
    }

    @Override
    public Proyectos update(Proyectos proyecto) {
        dsl.update(PROYECTOS)
                .set(dsl.newRecord(PROYECTOS, proyecto))
                .where(PROYECTOS.ID.eq(proyecto.getId()))
                .execute();
        return findById(proyecto.getId()).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(id))
                .execute();
    }

    @Override
    public boolean exists(Integer id) {
        return dsl.fetchExists(dsl.selectOne().from(PROYECTOS).where(PROYECTOS.ID.eq(id)));
    }
}
