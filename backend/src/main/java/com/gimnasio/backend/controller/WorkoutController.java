package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.WorkoutDto;
import com.gimnasio.backend.entity.Workout;
import com.gimnasio.backend.repository.WorkoutRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WorkoutController {
    
    private final WorkoutRepository workoutRepository;
    
    @PostMapping
    public ResponseEntity<WorkoutDto.Response> createWorkout(@Valid @RequestBody WorkoutDto.CreateRequest request) {
        // Implementation would go here
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<WorkoutDto.Response>> getAllWorkouts() {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto.Response> getWorkoutById(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDto.Response> updateWorkout(@PathVariable Long id, @Valid @RequestBody WorkoutDto.UpdateRequest request) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.noContent().build();
    }
}
