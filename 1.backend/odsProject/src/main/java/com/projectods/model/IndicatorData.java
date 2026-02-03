package com.projectods.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Modelo de datos para representar un indicador ODS
 * Contiene la información básica de cada indicador con sus valores actuales y objetivos
 */
public class IndicatorData {
    
    private String indicatorCode;
    private String title;
    private String description;
    private BigDecimal currentValue;
    private BigDecimal targetValue;
    private String unit;
    private String dataSource;
    private LocalDateTime lastUpdated;
    private String objectiveId;
    
    // Constructor vacío
    public IndicatorData() {}
    
    // Constructor completo
    public IndicatorData(String indicatorCode, String title, String description, 
                        BigDecimal currentValue, BigDecimal targetValue, String unit) {
        this.indicatorCode = indicatorCode;
        this.title = title;
        this.description = description;
        this.currentValue = currentValue;
        this.targetValue = targetValue;
        this.unit = unit;
        this.lastUpdated = LocalDateTime.now();
    }
    
    // Getters y Setters
    public String getIndicatorCode() {
        return indicatorCode;
    }
    
    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getCurrentValue() {
        return currentValue;
    }
    
    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }
    
    public BigDecimal getTargetValue() {
        return targetValue;
    }
    
    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getDataSource() {
        return dataSource;
    }
    
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getObjectiveId() {
        return objectiveId;
    }
    
    public void setObjectiveId(String objectiveId) {
        this.objectiveId = objectiveId;
    }
    
    /**
     * Calcula el porcentaje de progreso hacia el objetivo
     * @return Porcentaje de progreso (0-100)
     */
    public BigDecimal getProgressPercentage() {
        if (targetValue == null || targetValue.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal progress = currentValue.divide(targetValue, 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100"));
        
        // Limitar entre 0 y 100
        if (progress.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        if (progress.compareTo(new BigDecimal("100")) > 0) {
            return new BigDecimal("100");
        }
        
        return progress;
    }
    
    /**
     * Verifica si el indicador ha alcanzado su objetivo
     * @return true si se alcanzó o superó el objetivo
     */
    public boolean isTargetAchieved() {
        if (targetValue == null || currentValue == null) {
            return false;
        }
        
        // Para la mayoría de los indicadores, un valor menor es mejor
        // (pobreza, mortalidad, etc.)
        return currentValue.compareTo(targetValue) <= 0;
    }
    
    @Override
    public String toString() {
        return "IndicatorData{" +
                "indicatorCode='" + indicatorCode + '\'' +
                ", title='" + title + '\'' +
                ", currentValue=" + currentValue +
                ", targetValue=" + targetValue +
                ", unit='" + unit + '\'' +
                ", progress=" + getProgressPercentage() + "%" +
                '}';
    }
}
