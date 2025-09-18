package com.gimnasio.backend.dto;

import com.gimnasio.backend.entity.Attendance;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class AttendanceDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        @NotNull(message = "Member ID is required")
        private Long memberId;
        
        private LocalDateTime checkInTime;
        
        @NotNull(message = "Attendance type is required")
        private Attendance.AttendanceType attendanceType;
        
        private String purpose;
        private String notes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRequest {
        private LocalDateTime checkInTime;
        private LocalDateTime checkOutTime;
        private Attendance.AttendanceType attendanceType;
        private String purpose;
        private String notes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckOutRequest {
        private LocalDateTime checkOutTime;
        private String notes;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private LocalDateTime checkInTime;
        private LocalDateTime checkOutTime;
        private Integer durationMinutes;
        private Attendance.AttendanceType attendanceType;
        private String purpose;
        private String notes;
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
        private LocalDateTime checkInTime;
        private LocalDateTime checkOutTime;
        private Attendance.AttendanceType attendanceType;
        private String memberName;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticsResponse {
        private Long totalCheckIns;
        private Long totalCheckOuts;
        private Long activeMembers;
        private Double averageDurationMinutes;
        private Long checkInsToday;
        private Long checkInsThisWeek;
        private Long checkInsThisMonth;
    }
}

