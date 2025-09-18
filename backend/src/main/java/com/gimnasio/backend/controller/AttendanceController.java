package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.AttendanceDto;
import com.gimnasio.backend.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AttendanceController {
    
    private final AttendanceService attendanceService;
    
    @PostMapping("/check-in")
    public ResponseEntity<AttendanceDto.Response> checkIn(@Valid @RequestBody AttendanceDto.CreateRequest request) {
        try {
            AttendanceDto.Response response = attendanceService.checkIn(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}/check-out")
    public ResponseEntity<AttendanceDto.Response> checkOut(@PathVariable Long id, @Valid @RequestBody AttendanceDto.CheckOutRequest request) {
        try {
            AttendanceDto.Response response = attendanceService.checkOut(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<AttendanceDto.Response>> getAllAttendance() {
        List<AttendanceDto.Response> attendance = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendance);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto.Response> getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id)
                .map(attendance -> ResponseEntity.ok(attendance))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<AttendanceDto.Response>> getAttendanceByMember(@PathVariable Long memberId) {
        List<AttendanceDto.Response> attendance = attendanceService.getAttendanceByMember(memberId);
        return ResponseEntity.ok(attendance);
    }
    
    @GetMapping("/member/{memberId}/active")
    public ResponseEntity<List<AttendanceDto.Response>> getActiveAttendanceByMember(@PathVariable Long memberId) {
        List<AttendanceDto.Response> attendance = attendanceService.getActiveAttendanceByMember(memberId);
        return ResponseEntity.ok(attendance);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<AttendanceDto.Response>> getAttendanceInDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<AttendanceDto.Response> attendance = attendanceService.getAttendanceInDateRange(startDate, endDate);
        return ResponseEntity.ok(attendance);
    }
    
    @GetMapping("/statistics")
    public ResponseEntity<AttendanceDto.StatisticsResponse> getAttendanceStatistics() {
        AttendanceDto.StatisticsResponse statistics = attendanceService.getAttendanceStatistics();
        return ResponseEntity.ok(statistics);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        try {
            attendanceService.deleteAttendance(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

