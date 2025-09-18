package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.ScheduleDto;
import com.gimnasio.backend.entity.Schedule;
import com.gimnasio.backend.repository.ScheduleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ScheduleController {
    
    private final ScheduleRepository scheduleRepository;
    
    @PostMapping
    public ResponseEntity<ScheduleDto.Response> createSchedule(@Valid @RequestBody ScheduleDto.CreateRequest request) {
        // Implementation would go here
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<ScheduleDto.Response>> getAllSchedules() {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto.Response> getScheduleById(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto.Response> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDto.UpdateRequest request) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.noContent().build();
    }
}

