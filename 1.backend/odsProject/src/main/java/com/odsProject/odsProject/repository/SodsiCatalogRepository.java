package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.OdsCatalog;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiAliadoTipo;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiArea;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioCategoria;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioValor;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiDependencia;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiEjesPlanes;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiProvincias;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiRegionesMideplan;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiRolDependencia;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiUnidadesProgramaticas;
import com.odsProject.odsProject.repository.interfaces.ISodsiCatalogRepository;
import org.jooq.DSLContext;
import org.jooq.types.UByte;
import org.jooq.types.UShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods_login.tables.OdsCatalog.ODS_CATALOG;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiAliadoTipo.SODSI_ALIADO_TIPO;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiArea.SODSI_AREA;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiBeneficiarioCategoria.SODSI_BENEFICIARIO_CATEGORIA;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiBeneficiarioValor.SODSI_BENEFICIARIO_VALOR;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiDependencia.SODSI_DEPENDENCIA;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiEjesPlanes.SODSI_EJES_PLANES;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiProvincias.SODSI_PROVINCIAS;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiRegionesMideplan.SODSI_REGIONES_MIDEPLAN;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiRolDependencia.SODSI_ROL_DEPENDENCIA;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.SodsiUnidadesProgramaticas.SODSI_UNIDADES_PROGRAMATICAS;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.ProyectoBeneficiarios.PROYECTO_BENEFICIARIOS;

@Repository
public class SodsiCatalogRepository implements ISodsiCatalogRepository {

    @Autowired
    @Qualifier("dslOdsLogin")
    private DSLContext dsl;

    @Autowired
    @Qualifier("dslOdsMaster")
    private DSLContext dslMaster;

    @Override
    public List<OdsCatalog> findOdsCatalog() {
        return dsl.selectFrom(ODS_CATALOG)
                .orderBy(ODS_CATALOG.ID.asc())
                .fetchInto(OdsCatalog.class);
    }

    @Override
    public List<SodsiUnidadesProgramaticas> findUnidadesActivas() {
        return dsl.selectFrom(SODSI_UNIDADES_PROGRAMATICAS)
                .where(SODSI_UNIDADES_PROGRAMATICAS.ACTIVO.isTrue())
                .orderBy(SODSI_UNIDADES_PROGRAMATICAS.ORDEN, SODSI_UNIDADES_PROGRAMATICAS.NOMBRE)
                .fetchInto(SodsiUnidadesProgramaticas.class);
    }

    @Override
    public List<SodsiRegionesMideplan> findRegionesMideplan() {
        return dsl.selectFrom(SODSI_REGIONES_MIDEPLAN)
                .orderBy(SODSI_REGIONES_MIDEPLAN.ORDEN)
                .fetchInto(SodsiRegionesMideplan.class);
    }

    @Override
    public List<SodsiProvincias> findProvincias() {
        return dsl.selectFrom(SODSI_PROVINCIAS)
                .orderBy(SODSI_PROVINCIAS.ORDEN)
                .fetchInto(SodsiProvincias.class);
    }

    @Override
    public List<SodsiEjesPlanes> findEjesPlanes() {
        return dsl.selectFrom(SODSI_EJES_PLANES)
                .orderBy(SODSI_EJES_PLANES.ORDEN)
                .fetchInto(SodsiEjesPlanes.class);
    }

    @Override
    public List<SodsiAliadoTipo> findAliadoTipos() {
        return dsl.selectFrom(SODSI_ALIADO_TIPO)
                .orderBy(SODSI_ALIADO_TIPO.ORDEN)
                .fetchInto(SodsiAliadoTipo.class);
    }

    @Override
    public List<SodsiBeneficiarioCategoria> findBeneficiarioCategorias() {
        return dsl.selectFrom(SODSI_BENEFICIARIO_CATEGORIA)
                .orderBy(SODSI_BENEFICIARIO_CATEGORIA.ORDEN)
                .fetchInto(SodsiBeneficiarioCategoria.class);
    }

    @Override
    public List<SodsiBeneficiarioValor> findBeneficiarioValores() {
        return findBeneficiarioValoresActivos();
    }

    @Override
    public List<SodsiBeneficiarioValor> findBeneficiarioValoresActivos() {
        return dsl.selectFrom(SODSI_BENEFICIARIO_VALOR)
                .where(SODSI_BENEFICIARIO_VALOR.ACTIVO.isTrue())
                .orderBy(SODSI_BENEFICIARIO_VALOR.CATEGORIA_ID, SODSI_BENEFICIARIO_VALOR.ORDEN)
                .fetchInto(SodsiBeneficiarioValor.class);
    }

    @Override
    public List<SodsiBeneficiarioValor> findAllBeneficiarioValores() {
        return dsl.selectFrom(SODSI_BENEFICIARIO_VALOR)
                .orderBy(SODSI_BENEFICIARIO_VALOR.CATEGORIA_ID, SODSI_BENEFICIARIO_VALOR.ORDEN)
                .fetchInto(SodsiBeneficiarioValor.class);
    }

    @Override
    public List<SodsiBeneficiarioValor> findBeneficiarioValoresByIds(Collection<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return dsl.selectFrom(SODSI_BENEFICIARIO_VALOR)
                .where(SODSI_BENEFICIARIO_VALOR.ID.in(ids))
                .fetchInto(SodsiBeneficiarioValor.class);
    }

    @Override
    public Optional<SodsiBeneficiarioValor> findBeneficiarioValorById(UShort id) {
        if (id == null) return Optional.empty();
        return dsl.selectFrom(SODSI_BENEFICIARIO_VALOR)
                .where(SODSI_BENEFICIARIO_VALOR.ID.eq(id))
                .fetchOptionalInto(SodsiBeneficiarioValor.class);
    }

    @Override
    public int countProyectoReferencias(UShort valorId) {
        if (valorId == null) return 0;
        return dslMaster.fetchCount(
                PROYECTO_BENEFICIARIOS,
                PROYECTO_BENEFICIARIOS.VALOR_ID.eq(valorId));
    }

    @Override
    public SodsiBeneficiarioValor insertBeneficiarioValor(SodsiBeneficiarioValor valor) {
        var record = dsl.insertInto(SODSI_BENEFICIARIO_VALOR)
                .set(SODSI_BENEFICIARIO_VALOR.CATEGORIA_ID, valor.getCategoriaId())
                .set(SODSI_BENEFICIARIO_VALOR.CODIGO, valor.getCodigo())
                .set(SODSI_BENEFICIARIO_VALOR.NOMBRE, valor.getNombre())
                .set(SODSI_BENEFICIARIO_VALOR.ORDEN, valor.getOrden())
                .set(SODSI_BENEFICIARIO_VALOR.ACTIVO, valor.getActivo() != null ? valor.getActivo() : (byte) 1)
                .set(SODSI_BENEFICIARIO_VALOR.ES_PERSONALIZADO, valor.getEsPersonalizado() != null ? valor.getEsPersonalizado() : (byte) 0)
                .set(SODSI_BENEFICIARIO_VALOR.CREADO_POR, valor.getCreadoPor())
                .returning()
                .fetchOne();
        return record != null ? record.into(SodsiBeneficiarioValor.class) : valor;
    }

    @Override
    public Optional<SodsiBeneficiarioValor> setBeneficiarioValorActivo(UShort id, boolean activo) {
        if (id == null) return Optional.empty();
        int updated = dsl.update(SODSI_BENEFICIARIO_VALOR)
                .set(SODSI_BENEFICIARIO_VALOR.ACTIVO, activo ? (byte) 1 : (byte) 0)
                .where(SODSI_BENEFICIARIO_VALOR.ID.eq(id))
                .execute();
        if (updated == 0) return Optional.empty();
        return findBeneficiarioValorById(id);
    }

    @Override
    public Optional<SodsiBeneficiarioCategoria> findBeneficiarioCategoriaById(UByte id) {
        if (id == null) return Optional.empty();
        return dsl.selectFrom(SODSI_BENEFICIARIO_CATEGORIA)
                .where(SODSI_BENEFICIARIO_CATEGORIA.ID.eq(id))
                .fetchOptionalInto(SodsiBeneficiarioCategoria.class);
    }

    @Override
    public List<SodsiArea> findAreasActivas() {
        return dsl.selectFrom(SODSI_AREA)
                .where(SODSI_AREA.ACTIVO.isTrue())
                .orderBy(SODSI_AREA.ORDEN, SODSI_AREA.NOMBRE)
                .fetchInto(SodsiArea.class);
    }

    @Override
    public List<SodsiDependencia> findDependenciasActivas() {
        return dsl.selectFrom(SODSI_DEPENDENCIA)
                .where(SODSI_DEPENDENCIA.ACTIVO.isTrue())
                .orderBy(SODSI_DEPENDENCIA.ORDEN, SODSI_DEPENDENCIA.NOMBRE)
                .fetchInto(SodsiDependencia.class);
    }

    @Override
    public List<SodsiRolDependencia> findRolesDependencia() {
        return dsl.selectFrom(SODSI_ROL_DEPENDENCIA)
                .where(SODSI_ROL_DEPENDENCIA.ACTIVO.isTrue())
                .orderBy(SODSI_ROL_DEPENDENCIA.ORDEN, SODSI_ROL_DEPENDENCIA.NOMBRE)
                .fetchInto(SodsiRolDependencia.class);
    }

    @Override
    public Optional<SodsiArea> findAreaById(UShort id) {
        if (id == null) return Optional.empty();
        return dsl.selectFrom(SODSI_AREA).where(SODSI_AREA.ID.eq(id)).fetchOptionalInto(SodsiArea.class);
    }

    @Override
    public Optional<SodsiDependencia> findDependenciaById(UShort id) {
        if (id == null) return Optional.empty();
        return dsl.selectFrom(SODSI_DEPENDENCIA).where(SODSI_DEPENDENCIA.ID.eq(id)).fetchOptionalInto(SodsiDependencia.class);
    }

    @Override
    public Optional<SodsiRolDependencia> findRolDependenciaById(UByte id) {
        if (id == null) return Optional.empty();
        return dsl.selectFrom(SODSI_ROL_DEPENDENCIA).where(SODSI_ROL_DEPENDENCIA.ID.eq(id)).fetchOptionalInto(SodsiRolDependencia.class);
    }
}
