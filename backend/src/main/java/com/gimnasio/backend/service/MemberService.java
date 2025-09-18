package com.gimnasio.backend.service;

import com.gimnasio.backend.dto.MemberDto;
import com.gimnasio.backend.entity.Member;
import com.gimnasio.backend.entity.User;
import com.gimnasio.backend.repository.MemberRepository;
import com.gimnasio.backend.repository.UserRepository;
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
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    
    public MemberDto.Response createMember(MemberDto.CreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (memberRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new RuntimeException("Member already exists for this user");
        }
        
        Member member = new Member();
        member.setUser(user);
        member.setMemberNumber(generateMemberNumber());
        member.setDateOfBirth(request.getDateOfBirth());
        member.setEmergencyContact(request.getEmergencyContact());
        member.setEmergencyPhone(request.getEmergencyPhone());
        member.setMedicalConditions(request.getMedicalConditions());
        member.setFitnessGoals(request.getFitnessGoals());
        member.setHeightCm(request.getHeightCm());
        member.setWeightKg(request.getWeightKg());
        member.setBodyFatPercentage(request.getBodyFatPercentage());
        member.setMuscleMassKg(request.getMuscleMassKg());
        member.setStatus(request.getStatus() != null ? request.getStatus() : Member.MembershipStatus.ACTIVE);
        member.setJoinDate(request.getJoinDate() != null ? request.getJoinDate() : LocalDate.now());
        
        Member savedMember = memberRepository.save(member);
        return convertToResponse(savedMember);
    }
    
    @Transactional(readOnly = true)
    public List<MemberDto.Response> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Optional<MemberDto.Response> getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Optional<MemberDto.Response> getMemberByMemberNumber(String memberNumber) {
        return memberRepository.findByMemberNumber(memberNumber)
                .map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Optional<MemberDto.Response> getMemberByUserId(Long userId) {
        return memberRepository.findByUserId(userId)
                .map(this::convertToResponse);
    }
    
    public MemberDto.Response updateMember(Long id, MemberDto.UpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        
        if (request.getDateOfBirth() != null) {
            member.setDateOfBirth(request.getDateOfBirth());
        }
        
        if (request.getEmergencyContact() != null) {
            member.setEmergencyContact(request.getEmergencyContact());
        }
        
        if (request.getEmergencyPhone() != null) {
            member.setEmergencyPhone(request.getEmergencyPhone());
        }
        
        if (request.getMedicalConditions() != null) {
            member.setMedicalConditions(request.getMedicalConditions());
        }
        
        if (request.getFitnessGoals() != null) {
            member.setFitnessGoals(request.getFitnessGoals());
        }
        
        if (request.getHeightCm() != null) {
            member.setHeightCm(request.getHeightCm());
        }
        
        if (request.getWeightKg() != null) {
            member.setWeightKg(request.getWeightKg());
        }
        
        if (request.getBodyFatPercentage() != null) {
            member.setBodyFatPercentage(request.getBodyFatPercentage());
        }
        
        if (request.getMuscleMassKg() != null) {
            member.setMuscleMassKg(request.getMuscleMassKg());
        }
        
        if (request.getStatus() != null) {
            member.setStatus(request.getStatus());
        }
        
        Member updatedMember = memberRepository.save(member);
        return convertToResponse(updatedMember);
    }
    
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<MemberDto.Response> getMembersByStatus(Member.MembershipStatus status) {
        return memberRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<MemberDto.Response> searchMembersByName(String name) {
        return memberRepository.findByNameContaining(name).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<MemberDto.SummaryResponse> getMemberSummaries() {
        return memberRepository.findAll().stream()
                .map(this::convertToSummaryResponse)
                .collect(Collectors.toList());
    }
    
    private String generateMemberNumber() {
        String prefix = "MEM";
        long count = memberRepository.count() + 1;
        return prefix + String.format("%06d", count);
    }
    
    private MemberDto.Response convertToResponse(Member member) {
        MemberDto.Response response = new MemberDto.Response();
        response.setId(member.getId());
        response.setUserId(member.getUser().getId());
        response.setMemberNumber(member.getMemberNumber());
        response.setDateOfBirth(member.getDateOfBirth());
        response.setEmergencyContact(member.getEmergencyContact());
        response.setEmergencyPhone(member.getEmergencyPhone());
        response.setMedicalConditions(member.getMedicalConditions());
        response.setFitnessGoals(member.getFitnessGoals());
        response.setHeightCm(member.getHeightCm());
        response.setWeightKg(member.getWeightKg());
        response.setBodyFatPercentage(member.getBodyFatPercentage());
        response.setMuscleMassKg(member.getMuscleMassKg());
        response.setStatus(member.getStatus());
        response.setJoinDate(member.getJoinDate());
        response.setCreatedAt(member.getCreatedAt());
        response.setUpdatedAt(member.getUpdatedAt());
        
        // User information
        response.setFirstName(member.getUser().getFirstName());
        response.setLastName(member.getUser().getLastName());
        response.setEmail(member.getUser().getEmail());
        response.setPhoneNumber(member.getUser().getPhoneNumber());
        
        return response;
    }
    
    private MemberDto.SummaryResponse convertToSummaryResponse(Member member) {
        MemberDto.SummaryResponse response = new MemberDto.SummaryResponse();
        response.setId(member.getId());
        response.setMemberNumber(member.getMemberNumber());
        response.setFirstName(member.getUser().getFirstName());
        response.setLastName(member.getUser().getLastName());
        response.setEmail(member.getUser().getEmail());
        response.setStatus(member.getStatus());
        response.setJoinDate(member.getJoinDate());
        return response;
    }
}
