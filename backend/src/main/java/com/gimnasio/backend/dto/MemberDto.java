package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "User ID is required")
        private Long userId;
        
        private LocalDate dateOfBirth;
        private String emergencyContact;
        private String emergencyPhone;
        private String medicalConditions;
        private String fitnessGoals;
        private Double heightCm;
        private Double weightKg;
        private Double bodyFatPercentage;
        private Double muscleMassKg;
        private Member.MembershipStatus status;
        private LocalDate joinDate;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private LocalDate dateOfBirth;
        private String emergencyContact;
        private String emergencyPhone;
        private String medicalConditions;
        private String fitnessGoals;
        private Double heightCm;
        private Double weightKg;
        private Double bodyFatPercentage;
        private Double muscleMassKg;
        private Member.MembershipStatus status;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long userId;
        private String memberNumber;
        private LocalDate dateOfBirth;
        private String emergencyContact;
        private String emergencyPhone;
        private String medicalConditions;
        private String fitnessGoals;
        private Double heightCm;
        private Double weightKg;
        private Double bodyFatPercentage;
        private Double muscleMassKg;
        private Member.MembershipStatus status;
        private LocalDate joinDate;
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
        private String memberNumber;
        private String firstName;
        private String lastName;
        private String email;
        private Member.MembershipStatus status;
        private LocalDate joinDate;
    }
}

