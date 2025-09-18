package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Trainer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrainerDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "User ID is required")
        private Long userId;
        
        private String specialization;
        private String certifications;
        private Integer experienceYears;
        
        @DecimalMin(value = "0.0", message = "Hourly rate must be positive")
        private BigDecimal hourlyRate;
        
        private String bio;
        private Integer maxClientsPerDay;
        private Trainer.TrainerStatus status;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String specialization;
        private String certifications;
        private Integer experienceYears;
        
        @DecimalMin(value = "0.0", message = "Hourly rate must be positive")
        private BigDecimal hourlyRate;
        
        private String bio;
        private Integer maxClientsPerDay;
        private Integer currentClientsCount;
        private Trainer.TrainerStatus status;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long userId;
        private String employeeId;
        private String specialization;
        private String certifications;
        private Integer experienceYears;
        private BigDecimal hourlyRate;
        private String bio;
        private Integer maxClientsPerDay;
        private Integer currentClientsCount;
        private Trainer.TrainerStatus status;
        private LocalDateTime hireDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        // User information
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Long id;
        private String employeeId;
        private String firstName;
        private String lastName;
        private String specialization;
        private Trainer.TrainerStatus status;
        private Integer currentClientsCount;
        private Integer maxClientsPerDay;
    }
}

