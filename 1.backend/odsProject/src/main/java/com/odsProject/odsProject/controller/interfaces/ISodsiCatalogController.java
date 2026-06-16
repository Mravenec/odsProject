package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api/sodsi/catalogos")
public interface ISodsiCatalogController {

    @GetMapping
    ResponseEntity<Map<String, Object>> getAllCatalogos();
}
