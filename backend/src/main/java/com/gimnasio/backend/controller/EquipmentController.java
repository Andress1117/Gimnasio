package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.EquipmentDto;
import com.gimnasio.backend.entity.Equipment;
import com.gimnasio.backend.service.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EquipmentController {
    
    private final EquipmentService equipmentService;
    
    @PostMapping
    public ResponseEntity<EquipmentDto.Response> createEquipment(@Valid @RequestBody EquipmentDto.CreateRequest request) {
        try {
            EquipmentDto.Response response = equipmentService.createEquipment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<EquipmentDto.Response>> getAllEquipment() {
        List<EquipmentDto.Response> equipment = equipmentService.getAllEquipment();
        return ResponseEntity.ok(equipment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDto.Response> getEquipmentById(@PathVariable Long id) {
        return equipmentService.getEquipmentById(id)
                .map(equipment -> ResponseEntity.ok(equipment))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<EquipmentDto.Response>> getEquipmentByType(@PathVariable Equipment.EquipmentType type) {
        List<EquipmentDto.Response> equipment = equipmentService.getEquipmentByType(type);
        return ResponseEntity.ok(equipment);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<EquipmentDto.Response>> getEquipmentByStatus(@PathVariable Equipment.EquipmentStatus status) {
        List<EquipmentDto.Response> equipment = equipmentService.getEquipmentByStatus(status);
        return ResponseEntity.ok(equipment);
    }
    
    @GetMapping("/maintenance-due")
    public ResponseEntity<List<EquipmentDto.Response>> getEquipmentDueForMaintenance() {
        List<EquipmentDto.Response> equipment = equipmentService.getEquipmentDueForMaintenance();
        return ResponseEntity.ok(equipment);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDto.Response> updateEquipment(@PathVariable Long id, @Valid @RequestBody EquipmentDto.UpdateRequest request) {
        try {
            EquipmentDto.Response response = equipmentService.updateEquipment(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

