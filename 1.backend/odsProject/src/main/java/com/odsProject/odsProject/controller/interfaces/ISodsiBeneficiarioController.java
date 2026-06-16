package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/sodsi/beneficiarios")
public interface ISodsiBeneficiarioController {

    @GetMapping("/valores")
    ResponseEntity<List<Map<String, Object>>> listValores(
            @RequestParam(name = "activo", required = false) String activoFilter,
            @RequestHeader(value = "Authorization", required = false) String authorization);

    @PostMapping("/valores")
    ResponseEntity<Map<String, Object>> crearValor(
            @RequestBody Map<String, Object> body,
            @RequestHeader(value = "Authorization", required = false) String authorization);

    @PatchMapping("/valores/{id}/activo")
    ResponseEntity<Map<String, Object>> setActivo(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body,
            @RequestHeader(value = "Authorization", required = false) String authorization);
}
