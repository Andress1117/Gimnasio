package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
    List<Schedule> findByTrainerId(Long trainerId);
    
    List<Schedule> findByDayOfWeek(DayOfWeek dayOfWeek);
    
    List<Schedule> findByStatus(Schedule.ScheduleStatus status);
    
    @Query("SELECT s FROM Schedule s WHERE s.trainer.id = :trainerId AND s.status = 'ACTIVE'")
    List<Schedule> findActiveSchedulesByTrainer(@Param("trainerId") Long trainerId);
    
    @Query("SELECT s FROM Schedule s WHERE s.dayOfWeek = :dayOfWeek AND s.status = 'ACTIVE'")
    List<Schedule> findActiveSchedulesByDayOfWeek(@Param("dayOfWeek") DayOfWeek dayOfWeek);
    
    @Query("SELECT s FROM Schedule s WHERE s.trainer.id = :trainerId AND s.dayOfWeek = :dayOfWeek AND s.status = 'ACTIVE'")
    List<Schedule> findActiveSchedulesByTrainerAndDay(@Param("trainerId") Long trainerId, @Param("dayOfWeek") DayOfWeek dayOfWeek);
    
    @Query("SELECT s FROM Schedule s WHERE s.effectiveFrom <= :date AND (s.effectiveUntil IS NULL OR s.effectiveUntil >= :date) AND s.status = 'ACTIVE'")
    List<Schedule> findEffectiveSchedulesOnDate(@Param("date") LocalDateTime date);
    
    @Query("SELECT COUNT(s) FROM Schedule s WHERE s.status = :status")
    long countByStatus(@Param("status") Schedule.ScheduleStatus status);
}
