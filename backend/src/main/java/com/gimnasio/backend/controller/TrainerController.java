package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.TrainerDto;
import com.gimnasio.backend.entity.Trainer;
import com.gimnasio.backend.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrainerController {
    
    private final TrainerService trainerService;
    
    @PostMapping
    public ResponseEntity<TrainerDto.Response> createTrainer(@Valid @RequestBody TrainerDto.CreateRequest request) {
        try {
            TrainerDto.Response response = trainerService.createTrainer(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<TrainerDto.Response>> getAllTrainers() {
        List<TrainerDto.Response> trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok(trainers);
    }
    
    @GetMapping("/summaries")
    public ResponseEntity<List<TrainerDto.SummaryResponse>> getTrainerSummaries() {
        List<TrainerDto.SummaryResponse> summaries = trainerService.getTrainerSummaries();
        return ResponseEntity.ok(summaries);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto.Response> getTrainerById(@PathVariable Long id) {
        return trainerService.getTrainerById(id)
                .map(trainer -> ResponseEntity.ok(trainer))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/employee-id/{employeeId}")
    public ResponseEntity<TrainerDto.Response> getTrainerByEmployeeId(@PathVariable String employeeId) {
        return trainerService.getTrainerByEmployeeId(employeeId)
                .map(trainer -> ResponseEntity.ok(trainer))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<TrainerDto.Response> getTrainerByUserId(@PathVariable Long userId) {
        return trainerService.getTrainerByUserId(userId)
                .map(trainer -> ResponseEntity.ok(trainer))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TrainerDto.Response> updateTrainer(@PathVariable Long id, @Valid @RequestBody TrainerDto.UpdateRequest request) {
        try {
            TrainerDto.Response response = trainerService.updateTrainer(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TrainerDto.Response>> getTrainersByStatus(@PathVariable Trainer.TrainerStatus status) {
        List<TrainerDto.Response> trainers = trainerService.getTrainersByStatus(status);
        return ResponseEntity.ok(trainers);
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<TrainerDto.Response>> getAvailableTrainers() {
        List<TrainerDto.Response> trainers = trainerService.getAvailableTrainers();
        return ResponseEntity.ok(trainers);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TrainerDto.Response>> searchTrainersByName(@RequestParam String name) {
        List<TrainerDto.Response> trainers = trainerService.searchTrainersByName(name);
        return ResponseEntity.ok(trainers);
    }
}
