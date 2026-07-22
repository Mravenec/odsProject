package com.odsProject.odsProject.repository.interfaces;

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
import org.jooq.types.UByte;
import org.jooq.types.UShort;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Lectura de catálogos SODSI (ods_login).
 */
public interface ISodsiCatalogRepository {

    List<OdsCatalog> findOdsCatalog();

    List<SodsiUnidadesProgramaticas> findUnidadesActivas();

    List<SodsiRegionesMideplan> findRegionesMideplan();

    List<SodsiProvincias> findProvincias();

    List<SodsiEjesPlanes> findEjesPlanes();

    List<SodsiAliadoTipo> findAliadoTipos();

    List<SodsiBeneficiarioCategoria> findBeneficiarioCategorias();

    List<SodsiBeneficiarioValor> findBeneficiarioValores();

    /** Valores activos para UI gestor / GET catalogos. */
    List<SodsiBeneficiarioValor> findBeneficiarioValoresActivos();

    /** Todos los valores (admin catálogo). */
    List<SodsiBeneficiarioValor> findAllBeneficiarioValores();

    List<SodsiBeneficiarioValor> findBeneficiarioValoresByIds(java.util.Collection<Integer> ids);

    Optional<SodsiBeneficiarioValor> findBeneficiarioValorById(UShort id);

    int countProyectoReferencias(UShort valorId);

    SodsiBeneficiarioValor insertBeneficiarioValor(SodsiBeneficiarioValor valor);

    Optional<SodsiBeneficiarioValor> setBeneficiarioValorActivo(UShort id, boolean activo);

    Optional<SodsiBeneficiarioCategoria> findBeneficiarioCategoriaById(UByte id);

    List<SodsiArea> findAreasActivas();

    List<SodsiDependencia> findDependenciasActivas();

    List<SodsiRolDependencia> findRolesDependencia();

    Optional<SodsiArea> findAreaById(UShort id);

    Optional<SodsiDependencia> findDependenciaById(UShort id);

    Optional<SodsiRolDependencia> findRolDependenciaById(UByte id);

    Optional<SodsiUnidadesProgramaticas> findUnidadProgramaticaById(Integer id);
}
