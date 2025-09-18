package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Membership;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MembershipDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "Member ID is required")
        private Long memberId;
        
        @NotBlank(message = "Membership type is required")
        private String membershipType;
        
        private String description;
        
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", message = "Price must be positive")
        private BigDecimal price;
        
        @NotNull(message = "Duration in months is required")
        private Integer durationMonths;
        
        private LocalDate startDate;
        private Boolean autoRenewal;
        private String accessHoursStart;
        private String accessHoursEnd;
        private Integer guestPassesIncluded;
        private Integer personalTrainingSessions;
        private Boolean groupClassesIncluded;
        private Boolean lockerRentalIncluded;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String membershipType;
        private String description;
        
        @DecimalMin(value = "0.0", message = "Price must be positive")
        private BigDecimal price;
        
        private Integer durationMonths;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean autoRenewal;
        private Membership.MembershipStatus status;
        private String accessHoursStart;
        private String accessHoursEnd;
        private Integer guestPassesIncluded;
        private Integer guestPassesUsed;
        private Integer personalTrainingSessions;
        private Boolean groupClassesIncluded;
        private Boolean lockerRentalIncluded;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private String membershipType;
        private String description;
        private BigDecimal price;
        private Integer durationMonths;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean autoRenewal;
        private Membership.MembershipStatus status;
        private String accessHoursStart;
        private String accessHoursEnd;
        private Integer guestPassesIncluded;
        private Integer guestPassesUsed;
        private Integer personalTrainingSessions;
        private Boolean groupClassesIncluded;
        private Boolean lockerRentalIncluded;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        // Member information
        private String memberName;
        private String memberNumber;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Long id;
        private String membershipType;
        private BigDecimal price;
        private LocalDate startDate;
        private LocalDate endDate;
        private Membership.MembershipStatus status;
        private String memberName;
    }
}
