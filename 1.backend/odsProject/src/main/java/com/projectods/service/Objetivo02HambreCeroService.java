package com.projectods.service;

import com.projectods.service.interfaces.IObjetivo02HambreCeroService;
import com.projectods.repository.interfaces.IObjetivo02HambreCeroRepository;
import com.projectods.model.IndicatorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementación del Servicio para el Objetivo 2: Hambre Cero
 * Contiene la lógica de negocio para los indicadores del Objetivo de Desarrollo Sostenible 2
 */
@Service
public class Objetivo02HambreCeroService implements IObjetivo02HambreCeroService {

    @Autowired
    private IObjetivo02HambreCeroRepository objetivo02HambreCeroRepository;

    @Override
    public List<IndicatorData> getAllIndicators() {
        try {
            List<IndicatorData> indicators = new ArrayList<>();
            
            /** Obtiene todos los indicadores del Objetivo 2: Hambre Cero */
            indicators.add(getIndicador_2_1_1());
            indicators.add(getIndicador_2_1_2());
            indicators.add(getIndicador_2_2_1());
            indicators.add(getIndicador_2_2_2());
            indicators.add(getIndicador_2_2_3());
            indicators.add(getIndicador_2_2_4());
            indicators.add(getIndicador_2_3_1());
            indicators.add(getIndicador_2_3_2());
            indicators.add(getIndicador_2_4_1());
            indicators.add(getIndicador_2_5_1());
            indicators.add(getIndicador_2_5_2());
            indicators.add(getIndicador_2_a_1());
            indicators.add(getIndicador_2_a_2());
            indicators.add(getIndicador_2_b_1());
            indicators.add(getIndicador_2_c_1());
            
            return indicators;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los indicadores del Objetivo 2", e);
        }
    }

    @Override
    public IndicatorData getIndicador_2_1_1() {
        /** 2.1.1 Prevalencia de la subalimentación [10] */
        return objetivo02HambreCeroRepository.findIndicador_2_1_1();
    }

    @Override
    public IndicatorData getIndicador_2_1_2() {
        /** 2.1.2 Prevalencia de la inseguridad alimentaria moderada o grave entre la población, según la escala de experiencia de inseguridad alimentaria [10] */
        return objetivo02HambreCeroRepository.findIndicador_2_1_2();
    }

    @Override
    public IndicatorData getIndicador_2_2_1() {
        /** 2.2.1 Prevalencia del retraso del crecimiento (estatura para la edad, desviación típica < -2 de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años [11] */
        return objetivo02HambreCeroRepository.findIndicador_2_2_1();
    }

    @Override
    public IndicatorData getIndicador_2_2_2() {
        /** 2.2.2 Prevalencia de la malnutrición (peso para la estatura, desviación típica > +2 o < -2 de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años, desglosada por tipo (emaciación y sobrepeso) [11, 12] */
        return objetivo02HambreCeroRepository.findIndicador_2_2_2();
    }

    @Override
    public IndicatorData getIndicador_2_2_3() {
        /** 2.2.3 Prevalencia de la anemia en las mujeres de entre 15 y 49 años, desglosada por embarazo (porcentaje) [12] */
        return objetivo02HambreCeroRepository.findIndicador_2_2_3();
    }

    @Override
    public IndicatorData getIndicador_2_2_4() {
        /** 2.2.4 Prevalencia del umbral mínimo de diversidad alimentaria, por grupo de población (niños de 6 a 23,9 meses y mujeres no embarazadas de 15 a 49 años) [12] */
        return objetivo02HambreCeroRepository.findIndicador_2_2_4();
    }

    @Override
    public IndicatorData getIndicador_2_3_1() {
        /** 2.3.1 Volumen de producción por unidad de trabajo desglosado por tamaño y tipo de explotación (agropecuaria/ganadera/forestal) [13] */
        return objetivo02HambreCeroRepository.findIndicador_2_3_1();
    }

    @Override
    public IndicatorData getIndicador_2_3_2() {
        /** 2.3.2 Media de ingresos de los productores de alimentos en pequeña escala, desglosada por sexo y condición indígena [13] */
        return objetivo02HambreCeroRepository.findIndicador_2_3_2();
    }

    @Override
    public IndicatorData getIndicador_2_4_1() {
        /** 2.4.1 Proporción de la superficie agrícola en que se practica una agricultura productiva y sostenible [14] */
        return objetivo02HambreCeroRepository.findIndicador_2_4_1();
    }

    @Override
    public IndicatorData getIndicador_2_5_1() {
        /** 2.5.1 Número de: a) recursos genéticos vegetales y b) animales para la alimentación y la agricultura preservados en instalaciones de conservación a medio y largo plazo [15, 16] */
        return objetivo02HambreCeroRepository.findIndicador_2_5_1();
    }

    @Override
    public IndicatorData getIndicador_2_5_2() {
        /** 2.5.2 Proporción de razas y variedades locales y transfronterizas consideradas en riesgo de extinción [16] */
        return objetivo02HambreCeroRepository.findIndicador_2_5_2();
    }

    @Override
    public IndicatorData getIndicador_2_a_1() {
        /** 2.a.1 Índice de orientación agrícola para el gasto público [17] */
        return objetivo02HambreCeroRepository.findIndicador_2_a_1();
    }

    @Override
    public IndicatorData getIndicador_2_a_2() {
        /** 2.a.2 Total de corrientes oficiales de recursos (asistencia oficial para el desarrollo más otras corrientes oficiales) destinado al sector agrícola [17] */
        return objetivo02HambreCeroRepository.findIndicador_2_a_2();
    }

    @Override
    public IndicatorData getIndicador_2_b_1() {
        /** 2.b.1 Subsidios a la exportación de productos agropecuarios [18] */
        return objetivo02HambreCeroRepository.findIndicador_2_b_1();
    }

    @Override
    public IndicatorData getIndicador_2_c_1() {
        /** 2.c.1 Indicador de anomalías en los precios de los alimentos [18] */
        return objetivo02HambreCeroRepository.findIndicador_2_c_1();
    }
}
