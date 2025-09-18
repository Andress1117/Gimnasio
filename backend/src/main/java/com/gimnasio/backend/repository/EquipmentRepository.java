package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
    Optional<Equipment> findBySerialNumber(String serialNumber);
    
    boolean existsBySerialNumber(String serialNumber);
    
    List<Equipment> findByEquipmentType(Equipment.EquipmentType equipmentType);
    
    List<Equipment> findByStatus(Equipment.EquipmentStatus status);
    
    List<Equipment> findByBrand(String brand);
    
    List<Equipment> findByLocation(String location);
    
    @Query("SELECT e FROM Equipment e WHERE e.equipmentName LIKE %:name%")
    List<Equipment> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT e FROM Equipment e WHERE e.nextMaintenanceDate <= :date AND e.status = 'OPERATIONAL'")
    List<Equipment> findEquipmentDueForMaintenance(@Param("date") LocalDate date);
    
    @Query("SELECT e FROM Equipment e WHERE e.warrantyExpiryDate <= :date")
    List<Equipment> findEquipmentWithExpiredWarranty(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(e) FROM Equipment e WHERE e.status = :status")
    long countByStatus(@Param("status") Equipment.EquipmentStatus status);
    
    @Query("SELECT COUNT(e) FROM Equipment e WHERE e.equipmentType = :type")
    long countByEquipmentType(@Param("type") Equipment.EquipmentType type);
}

