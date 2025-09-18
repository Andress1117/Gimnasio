package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Equipment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EquipmentDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotBlank(message = "Equipment name is required")
        private String equipmentName;
        
        @NotBlank(message = "Brand is required")
        private String brand;
        
        @NotBlank(message = "Model is required")
        private String model;
        
        private String serialNumber;
        
        @NotNull(message = "Equipment type is required")
        private Equipment.EquipmentType equipmentType;
        
        @NotNull(message = "Purchase date is required")
        private LocalDate purchaseDate;
        
        @DecimalMin(value = "0.0", message = "Purchase price must be positive")
        private BigDecimal purchasePrice;
        
        private LocalDate warrantyExpiryDate;
        private LocalDate lastMaintenanceDate;
        private LocalDate nextMaintenanceDate;
        private String maintenanceNotes;
        private Equipment.EquipmentStatus status;
        private String location;
        private String description;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String equipmentName;
        private String brand;
        private String model;
        private String serialNumber;
        private Equipment.EquipmentType equipmentType;
        private LocalDate purchaseDate;
        
        @DecimalMin(value = "0.0", message = "Purchase price must be positive")
        private BigDecimal purchasePrice;
        
        private LocalDate warrantyExpiryDate;
        private LocalDate lastMaintenanceDate;
        private LocalDate nextMaintenanceDate;
        private String maintenanceNotes;
        private Equipment.EquipmentStatus status;
        private String location;
        private String description;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String equipmentName;
        private String brand;
        private String model;
        private String serialNumber;
        private Equipment.EquipmentType equipmentType;
        private LocalDate purchaseDate;
        private BigDecimal purchasePrice;
        private LocalDate warrantyExpiryDate;
        private LocalDate lastMaintenanceDate;
        private LocalDate nextMaintenanceDate;
        private String maintenanceNotes;
        private Equipment.EquipmentStatus status;
        private String location;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Long id;
        private String equipmentName;
        private String brand;
        private String model;
        private Equipment.EquipmentType equipmentType;
        private Equipment.EquipmentStatus status;
        private String location;
    }
}
