package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    
    List<Workout> findByMemberId(Long memberId);
    
    List<Workout> findByTrainerId(Long trainerId);
    
    List<Workout> findByWorkoutType(Workout.WorkoutType workoutType);
    
    List<Workout> findByStatus(Workout.WorkoutStatus status);
    
    List<Workout> findByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT w FROM Workout w WHERE w.member.id = :memberId AND w.status = 'COMPLETED'")
    List<Workout> findCompletedWorkoutsByMember(@Param("memberId") Long memberId);
    
    @Query("SELECT w FROM Workout w WHERE w.trainer.id = :trainerId AND w.startTime >= :startDate AND w.startTime <= :endDate")
    List<Workout> findWorkoutsByTrainerInDateRange(@Param("trainerId") Long trainerId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT w FROM Workout w WHERE w.member.id = :memberId AND w.startTime >= :startDate AND w.startTime <= :endDate")
    List<Workout> findWorkoutsByMemberInDateRange(@Param("memberId") Long memberId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT w FROM Workout w WHERE w.isPersonalTraining = true AND w.status = 'PLANNED'")
    List<Workout> findScheduledPersonalTrainingSessions();
    
    @Query("SELECT COUNT(w) FROM Workout w WHERE w.status = :status")
    long countByStatus(@Param("status") Workout.WorkoutStatus status);
    
    @Query("SELECT COUNT(w) FROM Workout w WHERE w.workoutType = :type")
    long countByWorkoutType(@Param("type") Workout.WorkoutType workoutType);
}

