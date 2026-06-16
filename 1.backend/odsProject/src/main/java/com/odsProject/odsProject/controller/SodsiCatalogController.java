package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.controller.interfaces.ISodsiCatalogController;
import com.odsProject.odsProject.service.interfaces.ISodsiCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SodsiCatalogController implements ISodsiCatalogController {

    @Autowired
    private ISodsiCatalogService sodsiCatalogService;

    @Override
    public ResponseEntity<Map<String, Object>> getAllCatalogos() {
        return ResponseEntity.ok(sodsiCatalogService.getAllCatalogos());
    }
}
