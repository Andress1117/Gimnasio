package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Workout;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "Member ID is required")
        private Long memberId;
        
        private Long trainerId;
        
        @NotBlank(message = "Workout name is required")
        private String workoutName;
        
        private String description;
        
        @NotNull(message = "Workout type is required")
        private Workout.WorkoutType workoutType;
        
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String notes;
        private Boolean isPersonalTraining;
        private List<ExerciseRequest> exercises;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private String workoutName;
        private String description;
        private Workout.WorkoutType workoutType;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer caloriesBurned;
        private String notes;
        private Workout.WorkoutStatus status;
        private Boolean isPersonalTraining;
        private List<ExerciseRequest> exercises;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private Long trainerId;
        private String workoutName;
        private String description;
        private Workout.WorkoutType workoutType;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer durationMinutes;
        private Integer caloriesBurned;
        private String notes;
        private Workout.WorkoutStatus status;
        private Boolean isPersonalTraining;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        // Member information
        private String memberName;
        private String memberNumber;
        
        // Trainer information
        private String trainerName;
        private String trainerEmployeeId;
        
        // Exercises
        private List<ExerciseResponse> exercises;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseRequest {
        private String exerciseName;
        private Integer sets;
        private Integer reps;
        private Double weightKg;
        private Integer durationSeconds;
        private Double distanceMeters;
        private String notes;
        private Integer orderIndex;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExerciseResponse {
        private Long id;
        private String exerciseName;
        private Integer sets;
        private Integer reps;
        private Double weightKg;
        private Integer durationSeconds;
        private Double distanceMeters;
        private String notes;
        private Integer orderIndex;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Long id;
        private String workoutName;
        private Workout.WorkoutType workoutType;
        private LocalDateTime startTime;
        private Workout.WorkoutStatus status;
        private String memberName;
        private String trainerName;
    }
}
