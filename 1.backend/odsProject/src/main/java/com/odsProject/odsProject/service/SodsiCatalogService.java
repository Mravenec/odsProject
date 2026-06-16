package com.odsProject.odsProject.service;

import com.odsProject.odsProject.repository.interfaces.ISodsiCatalogRepository;
import com.odsProject.odsProject.service.interfaces.ISodsiCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SodsiCatalogService implements ISodsiCatalogService {

    @Autowired
    private ISodsiCatalogRepository sodsiCatalogRepository;

    @Override
    public Map<String, Object> getAllCatalogos() {
        Map<String, Object> out = new LinkedHashMap<>();
        out.put("unidades", sodsiCatalogRepository.findUnidadesActivas());
        out.put("regionesMideplan", sodsiCatalogRepository.findRegionesMideplan());
        out.put("provincias", sodsiCatalogRepository.findProvincias());
        out.put("ejesPlanes", sodsiCatalogRepository.findEjesPlanes());
        out.put("areas", sodsiCatalogRepository.findAreasActivas());
        out.put("dependencias", sodsiCatalogRepository.findDependenciasActivas());
        out.put("rolesDependencia", sodsiCatalogRepository.findRolesDependencia());
        out.put("aliadoTipos", sodsiCatalogRepository.findAliadoTipos());
        out.put("beneficiarioCategorias", sodsiCatalogRepository.findBeneficiarioCategorias());
        out.put("beneficiarioValores", sodsiCatalogRepository.findBeneficiarioValores());
        return out;
    }
}
