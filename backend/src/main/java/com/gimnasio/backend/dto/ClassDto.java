package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Class;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ClassDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "Trainer ID is required")
        private Long trainerId;
        
        @NotBlank(message = "Class name is required")
        private String className;
        
        private String description;
        
        @NotNull(message = "Class type is required")
        private Class.ClassType classType;
        
        @NotNull(message = "Start time is required")
        private LocalDateTime startTime;
        
        @NotNull(message = "End time is required")
        private LocalDateTime endTime;
        
        @Min(value = 1, message = "Max capacity must be at least 1")
        @Max(value = 50, message = "Max capacity cannot exceed 50")
        private Integer maxCapacity;
        
        @DecimalMin(value = "0.0", message = "Price must be positive")
        private BigDecimal price;
        
        private String room;
        private String equipmentNeeded;
        private Class.DifficultyLevel difficultyLevel;
        private String notes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String className;
        private String description;
        private Class.ClassType classType;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer maxCapacity;
        private Integer currentEnrollment;
        
        @DecimalMin(value = "0.0", message = "Price must be positive")
        private BigDecimal price;
        
        private String room;
        private String equipmentNeeded;
        private Class.DifficultyLevel difficultyLevel;
        private Class.ClassStatus status;
        private String notes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long trainerId;
        private String className;
        private String description;
        private Class.ClassType classType;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer durationMinutes;
        private Integer maxCapacity;
        private Integer currentEnrollment;
        private BigDecimal price;
        private String room;
        private String equipmentNeeded;
        private Class.DifficultyLevel difficultyLevel;
        private Class.ClassStatus status;
        private String notes;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        // Trainer information
        private String trainerName;
        private String trainerEmployeeId;
        
        // Enrollments
        private List<EnrollmentResponse> enrollments;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnrollmentRequest {
        @NotNull(message = "Member ID is required")
        private Long memberId;
        
        private String notes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnrollmentResponse {
        private Long id;
        private Long memberId;
        private LocalDateTime enrollmentDate;
        private Class.EnrollmentStatus status;
        private Boolean attended;
        private String notes;
        
        // Member information
        private String memberName;
        private String memberNumber;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Long id;
        private String className;
        private Class.ClassType classType;
        private LocalDateTime startTime;
        private Integer maxCapacity;
        private Integer currentEnrollment;
        private Class.ClassStatus status;
        private String trainerName;
    }
}

