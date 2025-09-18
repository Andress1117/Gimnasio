package com.gimnasio.backend.service;

import com.gimnasio.backend.dto.AttendanceDto;
import com.gimnasio.backend.entity.Attendance;
import com.gimnasio.backend.entity.Member;
import com.gimnasio.backend.repository.AttendanceRepository;
import com.gimnasio.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceService {
    
    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;
    
    public AttendanceDto.Response checkIn(AttendanceDto.CreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        
        // Check if member has active check-in
        List<Attendance> activeAttendance = attendanceRepository.findActiveAttendanceByMember(request.getMemberId());
        if (!activeAttendance.isEmpty()) {
            throw new RuntimeException("Member already has an active check-in");
        }
        
        Attendance attendance = new Attendance();
        attendance.setMember(member);
        attendance.setCheckInTime(request.getCheckInTime() != null ? request.getCheckInTime() : LocalDateTime.now());
        attendance.setAttendanceType(request.getAttendanceType());
        attendance.setPurpose(request.getPurpose());
        attendance.setNotes(request.getNotes());
        
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return convertToResponse(savedAttendance);
    }
    
    public AttendanceDto.Response checkOut(Long attendanceId, AttendanceDto.CheckOutRequest request) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        
        if (attendance.getCheckOutTime() != null) {
            throw new RuntimeException("Member already checked out");
        }
        
        attendance.setCheckOutTime(request.getCheckOutTime() != null ? request.getCheckOutTime() : LocalDateTime.now());
        attendance.setNotes(request.getNotes());
        
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return convertToResponse(updatedAttendance);
    }
    
    @Transactional(readOnly = true)
    public List<AttendanceDto.Response> getAllAttendance() {
        return attendanceRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Optional<AttendanceDto.Response> getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public List<AttendanceDto.Response> getAttendanceByMember(Long memberId) {
        return attendanceRepository.findByMemberId(memberId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<AttendanceDto.Response> getActiveAttendanceByMember(Long memberId) {
        return attendanceRepository.findActiveAttendanceByMember(memberId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<AttendanceDto.Response> getAttendanceInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return attendanceRepository.findAttendanceInDateRange(startDate, endDate).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public AttendanceDto.StatisticsResponse getAttendanceStatistics() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime startOfWeek = now.minusDays(7);
        LocalDateTime startOfMonth = now.minusDays(30);
        
        long totalCheckIns = attendanceRepository.count();
        long totalCheckOuts = attendanceRepository.countAttendanceInDateRange(now.minusYears(1), now);
        long activeMembers = attendanceRepository.findActiveAttendanceByMember(0L).size(); // This needs to be fixed
        long checkInsToday = attendanceRepository.countAttendanceInDateRange(startOfDay, now);
        long checkInsThisWeek = attendanceRepository.countAttendanceInDateRange(startOfWeek, now);
        long checkInsThisMonth = attendanceRepository.countAttendanceInDateRange(startOfMonth, now);
        
        AttendanceDto.StatisticsResponse stats = new AttendanceDto.StatisticsResponse();
        stats.setTotalCheckIns(totalCheckIns);
        stats.setTotalCheckOuts(totalCheckOuts);
        stats.setActiveMembers(activeMembers);
        stats.setAverageDurationMinutes(0.0); // Calculate based on actual data
        stats.setCheckInsToday(checkInsToday);
        stats.setCheckInsThisWeek(checkInsThisWeek);
        stats.setCheckInsThisMonth(checkInsThisMonth);
        
        return stats;
    }
    
    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance record not found");
        }
        attendanceRepository.deleteById(id);
    }
    
    private AttendanceDto.Response convertToResponse(Attendance attendance) {
        AttendanceDto.Response response = new AttendanceDto.Response();
        response.setId(attendance.getId());
        response.setMemberId(attendance.getMember().getId());
        response.setCheckInTime(attendance.getCheckInTime());
        response.setCheckOutTime(attendance.getCheckOutTime());
        response.setDurationMinutes(attendance.getDurationMinutes());
        response.setAttendanceType(attendance.getAttendanceType());
        response.setPurpose(attendance.getPurpose());
        response.setNotes(attendance.getNotes());
        response.setCreatedAt(attendance.getCreatedAt());
        response.setUpdatedAt(attendance.getUpdatedAt());
        
        // Member information
        response.setMemberName(attendance.getMember().getUser().getFirstName() + " " + attendance.getMember().getUser().getLastName());
        response.setMemberNumber(attendance.getMember().getMemberNumber());
        
        return response;
    }
}
