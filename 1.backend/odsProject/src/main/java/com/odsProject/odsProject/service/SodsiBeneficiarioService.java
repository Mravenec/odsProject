package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioValor;
import com.odsProject.odsProject.repository.interfaces.ISodsiCatalogRepository;
import com.odsProject.odsProject.service.interfaces.ISodsiBeneficiarioService;
import org.jooq.types.UByte;
import org.jooq.types.UShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SodsiBeneficiarioService implements ISodsiBeneficiarioService {

    private static final int CODIGO_PERSONALIZADO_MIN = 901;
    private static final int CODIGO_PERSONALIZADO_MAX = 999;

    @Autowired
    private ISodsiCatalogRepository sodsiCatalogRepository;

    @Override
    public List<SodsiBeneficiarioValor> listValores(boolean activosOnly, boolean adminView) {
        if (adminView && !activosOnly) {
            return sodsiCatalogRepository.findAllBeneficiarioValores();
        }
        return sodsiCatalogRepository.findBeneficiarioValoresActivos();
    }

    @Override
    public SodsiBeneficiarioValor crearValorPersonalizado(Integer categoriaId, String nombre, Integer creadoPorUserId) {
        if (categoriaId == null || categoriaId < 1) {
            throw new IllegalArgumentException("categoriaId es requerido");
        }
        String trimmed = nombre != null ? nombre.trim() : "";
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("nombre es requerido");
        }
        if (creadoPorUserId == null) {
            throw new IllegalArgumentException("Usuario autenticado requerido");
        }

        UByte catId = UByte.valueOf(categoriaId.byteValue());
        sodsiCatalogRepository.findBeneficiarioCategoriaById(catId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría beneficiario no encontrada: " + categoriaId));

        UShort codigo = UShort.valueOf(resolveNextCodigoPersonalizado());

        SodsiBeneficiarioValor valor = new SodsiBeneficiarioValor()
                .setCategoriaId(catId)
                .setCodigo(codigo)
                .setNombre(trimmed)
                .setOrden(UShort.valueOf((short) resolveNextOrden(categoriaId)))
                .setActivo((byte) 1)
                .setEsPersonalizado((byte) 1)
                .setCreadoPor(creadoPorUserId);

        return sodsiCatalogRepository.insertBeneficiarioValor(valor);
    }

    @Override
    public SodsiBeneficiarioValor setActivo(Integer valorId, boolean activo) {
        if (valorId == null) {
            throw new IllegalArgumentException("valorId es requerido");
        }
        UShort id = UShort.valueOf(valorId.shortValue());
        return sodsiCatalogRepository.setBeneficiarioValorActivo(id, activo)
                .orElseThrow(() -> new IllegalArgumentException("Valor beneficiario no encontrado: " + valorId));
    }

    @Override
    public Map<String, Object> toMap(SodsiBeneficiarioValor valor) {
        Map<String, Object> m = new LinkedHashMap<>();
        if (valor == null) return m;
        m.put("id", valor.getId() != null ? valor.getId().intValue() : null);
        m.put("categoriaId", valor.getCategoriaId() != null ? valor.getCategoriaId().intValue() : null);
        m.put("codigo", valor.getCodigo() != null ? valor.getCodigo().intValue() : null);
        m.put("nombre", valor.getNombre());
        m.put("orden", valor.getOrden() != null ? valor.getOrden().intValue() : null);
        m.put("activo", valor.getActivo() != null && valor.getActivo() == 1);
        m.put("esPersonalizado", valor.getEsPersonalizado() != null && valor.getEsPersonalizado() == 1);
        m.put("creadoPor", valor.getCreadoPor());
        m.put("createdAt", valor.getCreatedAt() != null ? valor.getCreatedAt().toString() : null);
        return m;
    }

    private int resolveNextCodigoPersonalizado() {
        Integer max = sodsiCatalogRepository.findAllBeneficiarioValores().stream()
                .map(v -> v.getCodigo() != null ? v.getCodigo().intValue() : 0)
                .filter(c -> c >= CODIGO_PERSONALIZADO_MIN)
                .max(Integer::compareTo)
                .orElse(CODIGO_PERSONALIZADO_MIN - 1);
        int next = Math.max(CODIGO_PERSONALIZADO_MIN, max + 1);
        if (next > CODIGO_PERSONALIZADO_MAX) {
            throw new IllegalStateException("Sin códigos personalizados disponibles (rango 901-999)");
        }
        return next;
    }

    private int resolveNextOrden(int categoriaId) {
        return sodsiCatalogRepository.findAllBeneficiarioValores().stream()
                .filter(v -> v.getCategoriaId() != null && v.getCategoriaId().intValue() == categoriaId)
                .map(v -> v.getOrden() != null ? v.getOrden().intValue() : 0)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }
}
