package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.ClassDto;
import com.gimnasio.backend.entity.Class;
import com.gimnasio.backend.repository.ClassRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClassController {
    
    private final ClassRepository classRepository;
    
    @PostMapping
    public ResponseEntity<ClassDto.Response> createClass(@Valid @RequestBody ClassDto.CreateRequest request) {
        // Implementation would go here
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<ClassDto.Response>> getAllClasses() {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClassDto.Response> getClassById(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClassDto.Response> updateClass(@PathVariable Long id, @Valid @RequestBody ClassDto.UpdateRequest request) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.noContent().build();
    }
}
