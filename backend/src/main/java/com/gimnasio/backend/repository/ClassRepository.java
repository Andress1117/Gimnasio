package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    
    List<Class> findByTrainerId(Long trainerId);
    
    List<Class> findByClassType(Class.ClassType classType);
    
    List<Class> findByStatus(Class.ClassStatus status);
    
    List<Class> findByDifficultyLevel(Class.DifficultyLevel difficultyLevel);
    
    List<Class> findByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT c FROM Class c WHERE c.startTime >= :startDate AND c.startTime <= :endDate AND c.status = 'SCHEDULED'")
    List<Class> findScheduledClassesInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT c FROM Class c WHERE c.currentEnrollment < c.maxCapacity AND c.status = 'SCHEDULED'")
    List<Class> findClassesWithAvailableSpots();
    
    @Query("SELECT c FROM Class c WHERE c.trainer.id = :trainerId AND c.startTime >= :startDate AND c.startTime <= :endDate")
    List<Class> findClassesByTrainerInDateRange(@Param("trainerId") Long trainerId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT c FROM Class c WHERE c.room = :room AND c.startTime >= :startDate AND c.startTime <= :endDate")
    List<Class> findClassesByRoomInDateRange(@Param("room") String room, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(c) FROM Class c WHERE c.status = :status")
    long countByStatus(@Param("status") Class.ClassStatus status);
    
    @Query("SELECT COUNT(c) FROM Class c WHERE c.classType = :type")
    long countByClassType(@Param("type") Class.ClassType classType);
}
