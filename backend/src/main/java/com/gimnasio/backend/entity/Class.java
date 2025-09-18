package com.gimnasio.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    @NotNull(message = "Trainer is required")
    private Trainer trainer;
    
    @Column(name = "class_name", nullable = false, length = 100)
    private String className;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;
    
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
    
    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;
    
    @Column(name = "max_capacity", nullable = false)
    @Min(value = 1, message = "Max capacity must be at least 1")
    @Max(value = 50, message = "Max capacity cannot exceed 50")
    private Integer maxCapacity;
    
    @Column(name = "current_enrollment")
    private Integer currentEnrollment = 0;
    
    @Column(name = "price", precision = 10, scale = 2)
    private java.math.BigDecimal price;
    
    @Column(name = "room", length = 50)
    private String room;
    
    @Column(name = "equipment_needed", columnDefinition = "TEXT")
    private String equipmentNeeded;
    
    @Column(name = "difficulty_level")
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassStatus status = ClassStatus.SCHEDULED;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassEnrollment> enrollments;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (startTime != null && endTime != null) {
            durationMinutes = (int) java.time.Duration.between(startTime, endTime).toMinutes();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum ClassType {
        YOGA, PILATES, SPINNING, ZUMBA, CROSSFIT, BODYBUILDING, AEROBICS, DANCE, MARTIAL_ARTS, FUNCTIONAL
    }
    
    public enum DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, ALL_LEVELS
    }
    
    public enum ClassStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED, POSTPONED
    }
    
    @Entity
    @Table(name = "class_enrollments")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClassEnrollment {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "class_id", nullable = false)
        private Class classEntity;
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id", nullable = false)
        private Member member;
        
        @Column(name = "enrollment_date", nullable = false)
        private LocalDateTime enrollmentDate;
        
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private EnrollmentStatus status = EnrollmentStatus.ENROLLED;
        
        @Column(name = "attended")
        private Boolean attended = false;
        
        @Column(name = "notes", columnDefinition = "TEXT")
        private String notes;
    }
    
    public enum EnrollmentStatus {
        ENROLLED, CANCELLED, NO_SHOW, COMPLETED
    }
}
