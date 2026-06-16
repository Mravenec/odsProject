package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioValor;

import java.util.List;
import java.util.Map;

public interface ISodsiBeneficiarioService {

    List<SodsiBeneficiarioValor> listValores(boolean activosOnly, boolean adminView);

    SodsiBeneficiarioValor crearValorPersonalizado(Integer categoriaId, String nombre, Integer creadoPorUserId);

    SodsiBeneficiarioValor setActivo(Integer valorId, boolean activo);

    Map<String, Object> toMap(SodsiBeneficiarioValor valor);
}
