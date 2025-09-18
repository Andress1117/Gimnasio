package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    
    Optional<Trainer> findByEmployeeId(String employeeId);
    
    Optional<Trainer> findByUserId(Long userId);
    
    boolean existsByEmployeeId(String employeeId);
    
    List<Trainer> findByStatus(Trainer.TrainerStatus status);
    
    List<Trainer> findBySpecializationContaining(String specialization);
    
    @Query("SELECT t FROM Trainer t WHERE t.user.firstName LIKE %:name% OR t.user.lastName LIKE %:name%")
    List<Trainer> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT t FROM Trainer t WHERE t.status = :status AND t.hireDate >= :fromDate")
    List<Trainer> findActiveTrainersSince(@Param("status") Trainer.TrainerStatus status, @Param("fromDate") LocalDateTime fromDate);
    
    @Query("SELECT t FROM Trainer t WHERE t.currentClientsCount < t.maxClientsPerDay AND t.status = 'ACTIVE'")
    List<Trainer> findAvailableTrainers();
    
    @Query("SELECT COUNT(t) FROM Trainer t WHERE t.status = :status")
    long countByStatus(@Param("status") Trainer.TrainerStatus status);
    
    @Query("SELECT t FROM Trainer t WHERE t.user.email = :email")
    Optional<Trainer> findByUserEmail(@Param("email") String email);
}

