package com.gimnasio.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Equipment name is required")
    @Column(name = "equipment_name", nullable = false, length = 100)
    private String equipmentName;
    
    @NotBlank(message = "Brand is required")
    @Column(nullable = false, length = 50)
    private String brand;
    
    @NotBlank(message = "Model is required")
    @Column(nullable = false, length = 50)
    private String model;
    
    @Column(name = "serial_number", unique = true)
    private String serialNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "equipment_type", nullable = false)
    private EquipmentType equipmentType;
    
    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;
    
    @Column(name = "purchase_price", precision = 10, scale = 2)
    @DecimalMin(value = "0.0", message = "Purchase price must be positive")
    private BigDecimal purchasePrice;
    
    @Column(name = "warranty_expiry_date")
    private LocalDate warrantyExpiryDate;
    
    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;
    
    @Column(name = "next_maintenance_date")
    private LocalDate nextMaintenanceDate;
    
    @Column(name = "maintenance_notes", columnDefinition = "TEXT")
    private String maintenanceNotes;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EquipmentStatus status = EquipmentStatus.OPERATIONAL;
    
    @Column(name = "location", length = 100)
    private String location;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum EquipmentType {
        CARDIO, STRENGTH, FUNCTIONAL, FREE_WEIGHTS, ACCESSORIES
    }
    
    public enum EquipmentStatus {
        OPERATIONAL, OUT_OF_ORDER, MAINTENANCE, RETIRED
    }
}
