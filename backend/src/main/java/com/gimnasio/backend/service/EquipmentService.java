package com.gimnasio.backend.service;

import com.gimnasio.backend.dto.EquipmentDto;
import com.gimnasio.backend.entity.Equipment;
import com.gimnasio.backend.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentService {
    
    private final EquipmentRepository equipmentRepository;
    
    public EquipmentDto.Response createEquipment(EquipmentDto.CreateRequest request) {
        if (request.getSerialNumber() != null && equipmentRepository.existsBySerialNumber(request.getSerialNumber())) {
            throw new RuntimeException("Serial number already exists");
        }
        
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(request.getEquipmentName());
        equipment.setBrand(request.getBrand());
        equipment.setModel(request.getModel());
        equipment.setSerialNumber(request.getSerialNumber());
        equipment.setEquipmentType(request.getEquipmentType());
        equipment.setPurchaseDate(request.getPurchaseDate());
        equipment.setPurchasePrice(request.getPurchasePrice());
        equipment.setWarrantyExpiryDate(request.getWarrantyExpiryDate());
        equipment.setLastMaintenanceDate(request.getLastMaintenanceDate());
        equipment.setNextMaintenanceDate(request.getNextMaintenanceDate());
        equipment.setMaintenanceNotes(request.getMaintenanceNotes());
        equipment.setStatus(request.getStatus() != null ? request.getStatus() : Equipment.EquipmentStatus.OPERATIONAL);
        equipment.setLocation(request.getLocation());
        equipment.setDescription(request.getDescription());
        
        Equipment savedEquipment = equipmentRepository.save(equipment);
        return convertToResponse(savedEquipment);
    }
    
    @Transactional(readOnly = true)
    public List<EquipmentDto.Response> getAllEquipment() {
        return equipmentRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Optional<EquipmentDto.Response> getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public List<EquipmentDto.Response> getEquipmentByType(Equipment.EquipmentType type) {
        return equipmentRepository.findByEquipmentType(type).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EquipmentDto.Response> getEquipmentByStatus(Equipment.EquipmentStatus status) {
        return equipmentRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<EquipmentDto.Response> getEquipmentDueForMaintenance() {
        return equipmentRepository.findEquipmentDueForMaintenance(LocalDate.now()).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public EquipmentDto.Response updateEquipment(Long id, EquipmentDto.UpdateRequest request) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        
        if (request.getEquipmentName() != null) {
            equipment.setEquipmentName(request.getEquipmentName());
        }
        
        if (request.getBrand() != null) {
            equipment.setBrand(request.getBrand());
        }
        
        if (request.getModel() != null) {
            equipment.setModel(request.getModel());
        }
        
        if (request.getSerialNumber() != null && !request.getSerialNumber().equals(equipment.getSerialNumber())) {
            if (equipmentRepository.existsBySerialNumber(request.getSerialNumber())) {
                throw new RuntimeException("Serial number already exists");
            }
            equipment.setSerialNumber(request.getSerialNumber());
        }
        
        if (request.getEquipmentType() != null) {
            equipment.setEquipmentType(request.getEquipmentType());
        }
        
        if (request.getPurchaseDate() != null) {
            equipment.setPurchaseDate(request.getPurchaseDate());
        }
        
        if (request.getPurchasePrice() != null) {
            equipment.setPurchasePrice(request.getPurchasePrice());
        }
        
        if (request.getWarrantyExpiryDate() != null) {
            equipment.setWarrantyExpiryDate(request.getWarrantyExpiryDate());
        }
        
        if (request.getLastMaintenanceDate() != null) {
            equipment.setLastMaintenanceDate(request.getLastMaintenanceDate());
        }
        
        if (request.getNextMaintenanceDate() != null) {
            equipment.setNextMaintenanceDate(request.getNextMaintenanceDate());
        }
        
        if (request.getMaintenanceNotes() != null) {
            equipment.setMaintenanceNotes(request.getMaintenanceNotes());
        }
        
        if (request.getStatus() != null) {
            equipment.setStatus(request.getStatus());
        }
        
        if (request.getLocation() != null) {
            equipment.setLocation(request.getLocation());
        }
        
        if (request.getDescription() != null) {
            equipment.setDescription(request.getDescription());
        }
        
        Equipment updatedEquipment = equipmentRepository.save(equipment);
        return convertToResponse(updatedEquipment);
    }
    
    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new RuntimeException("Equipment not found");
        }
        equipmentRepository.deleteById(id);
    }
    
    private EquipmentDto.Response convertToResponse(Equipment equipment) {
        EquipmentDto.Response response = new EquipmentDto.Response();
        response.setId(equipment.getId());
        response.setEquipmentName(equipment.getEquipmentName());
        response.setBrand(equipment.getBrand());
        response.setModel(equipment.getModel());
        response.setSerialNumber(equipment.getSerialNumber());
        response.setEquipmentType(equipment.getEquipmentType());
        response.setPurchaseDate(equipment.getPurchaseDate());
        response.setPurchasePrice(equipment.getPurchasePrice());
        response.setWarrantyExpiryDate(equipment.getWarrantyExpiryDate());
        response.setLastMaintenanceDate(equipment.getLastMaintenanceDate());
        response.setNextMaintenanceDate(equipment.getNextMaintenanceDate());
        response.setMaintenanceNotes(equipment.getMaintenanceNotes());
        response.setStatus(equipment.getStatus());
        response.setLocation(equipment.getLocation());
        response.setDescription(equipment.getDescription());
        response.setCreatedAt(equipment.getCreatedAt());
        response.setUpdatedAt(equipment.getUpdatedAt());
        return response;
    }
}

