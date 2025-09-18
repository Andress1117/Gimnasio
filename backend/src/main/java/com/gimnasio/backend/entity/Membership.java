package com.gimnasio.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "memberships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull(message = "Member is required")
    private Member member;
    
    @NotBlank(message = "Membership type is required")
    @Column(name = "membership_type", nullable = false, length = 50)
    private String membershipType;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private BigDecimal price;
    
    @Column(name = "duration_months", nullable = false)
    private Integer durationMonths;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "auto_renewal")
    private Boolean autoRenewal = false;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipStatus status = MembershipStatus.ACTIVE;
    
    @Column(name = "access_hours_start")
    private String accessHoursStart;
    
    @Column(name = "access_hours_end")
    private String accessHoursEnd;
    
    @Column(name = "guest_passes_included")
    private Integer guestPassesIncluded = 0;
    
    @Column(name = "guest_passes_used")
    private Integer guestPassesUsed = 0;
    
    @Column(name = "personal_training_sessions")
    private Integer personalTrainingSessions = 0;
    
    @Column(name = "group_classes_included")
    private Boolean groupClassesIncluded = true;
    
    @Column(name = "locker_rental_included")
    private Boolean lockerRentalIncluded = false;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (endDate == null && startDate != null && durationMonths != null) {
            endDate = startDate.plusMonths(durationMonths);
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum MembershipStatus {
        ACTIVE, EXPIRED, CANCELLED, SUSPENDED
    }
}
