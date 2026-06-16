package com.odsProject.odsProject.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class AdminUsersSodsiListSmokeTest {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    void listadoAdminIncluyeCamposSodsiParaGestorMock() {
        List<Map<String, Object>> users = loginRepository.findAllUsuariosAdmin();
        Optional<Map<String, Object>> gestor = users.stream()
                .filter(u -> "gestor_pobreza".equals(u.get("username")))
                .findFirst();

        Assertions.assertTrue(gestor.isPresent(), "gestor_pobreza debe existir en mocks");
        Map<String, Object> g = gestor.get();
        Assertions.assertNotNull(g.get("areaId"), "areaId no debe ser null");
        Assertions.assertNotNull(g.get("dependenciaId"), "dependenciaId no debe ser null");
        Assertions.assertNotNull(g.get("rolDependenciaId"), "rolDependenciaId no debe ser null");
        Assertions.assertNotNull(g.get("telefonoContacto"), "telefonoContacto no debe ser null");
        Assertions.assertEquals(6, ((Number) g.get("areaId")).intValue());
        Assertions.assertEquals(1, ((Number) g.get("dependenciaId")).intValue());
        Assertions.assertEquals(1, ((Number) g.get("rolDependenciaId")).intValue());
    }
}
