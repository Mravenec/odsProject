package com.projectods.controller;

import com.projectods.controller.interfaces.IObjetivo02HambreCeroController;
import com.projectods.service.interfaces.IObjetivo02HambreCeroService;
import com.projectods.model.IndicatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
 * Controlador REST para el Objetivo 2: Hambre Cero
 * Implementa los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 2
 */
@RestController
@RequestMapping("/api/objetivo02")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class Objetivo02HambreCeroController implements IObjetivo02HambreCeroController {

    @Autowired
    private IObjetivo02HambreCeroService objetivo02HambreCeroService;

    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<IndicatorData>> getAllIndicators() {
        try {
            List<IndicatorData> indicators = objetivo02HambreCeroService.getAllIndicators();
            return ResponseEntity.ok(indicators);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-1-1")
    public ResponseEntity<IndicatorData> getIndicador_2_1_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_1_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-1-2")
    public ResponseEntity<IndicatorData> getIndicador_2_1_2() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_1_2();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-2-1")
    public ResponseEntity<IndicatorData> getIndicador_2_2_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_2_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-2-2")
    public ResponseEntity<IndicatorData> getIndicador_2_2_2() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_2_2();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-2-3")
    public ResponseEntity<IndicatorData> getIndicador_2_2_3() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_2_3();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-2-4")
    public ResponseEntity<IndicatorData> getIndicador_2_2_4() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_2_4();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-3-1")
    public ResponseEntity<IndicatorData> getIndicador_2_3_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_3_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-3-2")
    public ResponseEntity<IndicatorData> getIndicador_2_3_2() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_3_2();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-4-1")
    public ResponseEntity<IndicatorData> getIndicador_2_4_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_4_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-5-1")
    public ResponseEntity<IndicatorData> getIndicador_2_5_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_5_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-5-2")
    public ResponseEntity<IndicatorData> getIndicador_2_5_2() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_5_2();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-a-1")
    public ResponseEntity<IndicatorData> getIndicador_2_a_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_a_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-a-2")
    public ResponseEntity<IndicatorData> getIndicador_2_a_2() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_a_2();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-b-1")
    public ResponseEntity<IndicatorData> getIndicador_2_b_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_b_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/indicador-2-c-1")
    public ResponseEntity<IndicatorData> getIndicador_2_c_1() {
        try {
            IndicatorData indicator = objetivo02HambreCeroService.getIndicador_2_c_1();
            return ResponseEntity.ok(indicator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
