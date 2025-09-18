package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Payment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "Member ID is required")
        private Long memberId;
        
        private Long membershipId;
        
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
        private BigDecimal amount;
        
        @NotNull(message = "Payment type is required")
        private Payment.PaymentType paymentType;
        
        @NotNull(message = "Payment method is required")
        private Payment.PaymentMethod paymentMethod;
        
        private LocalDateTime paymentDate;
        private String description;
        private String notes;
        private String processedBy;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
        private BigDecimal amount;
        
        private Payment.PaymentType paymentType;
        private Payment.PaymentMethod paymentMethod;
        private LocalDateTime paymentDate;
        private Payment.PaymentStatus status;
        private String transactionId;
        private String description;
        private String notes;
        private String processedBy;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private Long membershipId;
        private String paymentNumber;
        private BigDecimal amount;
        private Payment.PaymentType paymentType;
        private Payment.PaymentMethod paymentMethod;
        private LocalDateTime paymentDate;
        private Payment.PaymentStatus status;
        private String transactionId;
        private String description;
        private String notes;
        private String processedBy;
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
        private String paymentNumber;
        private BigDecimal amount;
        private Payment.PaymentType paymentType;
        private Payment.PaymentStatus status;
        private LocalDateTime paymentDate;
        private String memberName;
    }
}
