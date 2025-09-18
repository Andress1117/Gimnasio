package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
    List<Attendance> findByMemberId(Long memberId);
    
    List<Attendance> findByAttendanceType(Attendance.AttendanceType attendanceType);
    
    List<Attendance> findByCheckInTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT a FROM Attendance a WHERE a.member.id = :memberId AND a.checkInTime >= :startDate AND a.checkInTime <= :endDate")
    List<Attendance> findAttendanceByMemberInDateRange(@Param("memberId") Long memberId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT a FROM Attendance a WHERE a.checkInTime >= :startDate AND a.checkInTime <= :endDate")
    List<Attendance> findAttendanceInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT a FROM Attendance a WHERE a.member.id = :memberId AND a.checkOutTime IS NULL")
    List<Attendance> findActiveAttendanceByMember(@Param("memberId") Long memberId);
    
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.checkInTime >= :startDate AND a.checkInTime <= :endDate")
    long countAttendanceInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.member.id = :memberId AND a.checkInTime >= :startDate AND a.checkInTime <= :endDate")
    long countAttendanceByMemberInDateRange(@Param("memberId") Long memberId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT a FROM Attendance a WHERE a.attendanceType = :type AND a.checkInTime >= :startDate AND a.checkInTime <= :endDate")
    List<Attendance> findAttendanceByTypeInDateRange(@Param("type") Attendance.AttendanceType attendanceType, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

