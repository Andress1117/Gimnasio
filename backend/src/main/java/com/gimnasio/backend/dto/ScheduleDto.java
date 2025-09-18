package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Schedule;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduleDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "Trainer ID is required")
        private Long trainerId;
        
        @NotNull(message = "Day of week is required")
        private DayOfWeek dayOfWeek;
        
        @NotNull(message = "Start time is required")
        private LocalTime startTime;
        
        @NotNull(message = "End time is required")
        private LocalTime endTime;
        
        private LocalTime breakStartTime;
        private LocalTime breakEndTime;
        private Integer maxClientsPerSlot;
        private Integer slotDurationMinutes;
        private String notes;
        private LocalDateTime effectiveFrom;
        private LocalDateTime effectiveUntil;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalTime breakStartTime;
        private LocalTime breakEndTime;
        private Integer maxClientsPerSlot;
        private Integer slotDurationMinutes;
        private Schedule.ScheduleStatus status;
        private String notes;
        private LocalDateTime effectiveFrom;
        private LocalDateTime effectiveUntil;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long trainerId;
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
        private LocalTime breakStartTime;
        private LocalTime breakEndTime;
        private Integer maxClientsPerSlot;
        private Integer slotDurationMinutes;
        private Schedule.ScheduleStatus status;
        private String notes;
        private LocalDateTime effectiveFrom;
        private LocalDateTime effectiveUntil;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        // Trainer information
        private String trainerName;
        private String trainerEmployeeId;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryResponse {
        private Long id;
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
        private Schedule.ScheduleStatus status;
        private String trainerName;
    }
}

