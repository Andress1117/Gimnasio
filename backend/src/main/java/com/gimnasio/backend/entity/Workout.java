package com.gimnasio.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @NotNull(message = "Member is required")
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    
    @Column(name = "workout_name", nullable = false, length = 100)
    private String workoutName;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "workout_type", nullable = false)
    private WorkoutType workoutType;
    
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    
    @Column(name = "calories_burned")
    private Integer caloriesBurned;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutStatus status = WorkoutStatus.PLANNED;
    
    @Column(name = "is_personal_training")
    private Boolean isPersonalTraining = false;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkoutExercise> exercises;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        if (startTime != null && endTime != null) {
            durationMinutes = (int) java.time.Duration.between(startTime, endTime).toMinutes();
        }
    }
    
    public enum WorkoutType {
        STRENGTH, CARDIO, FLEXIBILITY, FUNCTIONAL, SPORTS, MIXED
    }
    
    public enum WorkoutStatus {
        PLANNED, IN_PROGRESS, COMPLETED, CANCELLED
    }
    
    @Entity
    @Table(name = "workout_exercises")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkoutExercise {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "workout_id", nullable = false)
        private Workout workout;
        
        @Column(name = "exercise_name", nullable = false, length = 100)
        private String exerciseName;
        
        @Column(name = "sets")
        private Integer sets;
        
        @Column(name = "reps")
        private Integer reps;
        
        @Column(name = "weight_kg")
        private Double weightKg;
        
        @Column(name = "duration_seconds")
        private Integer durationSeconds;
        
        @Column(name = "distance_meters")
        private Double distanceMeters;
        
        @Column(name = "notes", columnDefinition = "TEXT")
        private String notes;
        
        @Column(name = "order_index")
        private Integer orderIndex;
    }
}
