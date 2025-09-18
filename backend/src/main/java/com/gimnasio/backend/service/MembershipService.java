package com.gimnasio.backend.service;

import com.gimnasio.backend.dto.MembershipDto;
import com.gimnasio.backend.entity.Membership;
import com.gimnasio.backend.entity.Member;
import com.gimnasio.backend.repository.MembershipRepository;
import com.gimnasio.backend.repository.MemberRepository;
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
public class MembershipService {
    
    private final MembershipRepository membershipRepository;
    private final MemberRepository memberRepository;
    
    public MembershipDto.Response createMembership(MembershipDto.CreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));
        
        Membership membership = new Membership();
        membership.setMember(member);
        membership.setMembershipType(request.getMembershipType());
        membership.setDescription(request.getDescription());
        membership.setPrice(request.getPrice());
        membership.setDurationMonths(request.getDurationMonths());
        membership.setStartDate(request.getStartDate() != null ? request.getStartDate() : LocalDate.now());
        membership.setAutoRenewal(request.getAutoRenewal() != null ? request.getAutoRenewal() : false);
        membership.setStatus(Membership.MembershipStatus.ACTIVE);
        membership.setAccessHoursStart(request.getAccessHoursStart());
        membership.setAccessHoursEnd(request.getAccessHoursEnd());
        membership.setGuestPassesIncluded(request.getGuestPassesIncluded() != null ? request.getGuestPassesIncluded() : 0);
        membership.setPersonalTrainingSessions(request.getPersonalTrainingSessions() != null ? request.getPersonalTrainingSessions() : 0);
        membership.setGroupClassesIncluded(request.getGroupClassesIncluded() != null ? request.getGroupClassesIncluded() : true);
        membership.setLockerRentalIncluded(request.getLockerRentalIncluded() != null ? request.getLockerRentalIncluded() : false);
        
        Membership savedMembership = membershipRepository.save(membership);
        return convertToResponse(savedMembership);
    }
    
    @Transactional(readOnly = true)
    public List<MembershipDto.Response> getAllMemberships() {
        return membershipRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Optional<MembershipDto.Response> getMembershipById(Long id) {
        return membershipRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    public MembershipDto.Response updateMembership(Long id, MembershipDto.UpdateRequest request) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        
        if (request.getMembershipType() != null) {
            membership.setMembershipType(request.getMembershipType());
        }
        
        if (request.getDescription() != null) {
            membership.setDescription(request.getDescription());
        }
        
        if (request.getPrice() != null) {
            membership.setPrice(request.getPrice());
        }
        
        if (request.getDurationMonths() != null) {
            membership.setDurationMonths(request.getDurationMonths());
        }
        
        if (request.getStartDate() != null) {
            membership.setStartDate(request.getStartDate());
        }
        
        if (request.getEndDate() != null) {
            membership.setEndDate(request.getEndDate());
        }
        
        if (request.getAutoRenewal() != null) {
            membership.setAutoRenewal(request.getAutoRenewal());
        }
        
        if (request.getStatus() != null) {
            membership.setStatus(request.getStatus());
        }
        
        if (request.getAccessHoursStart() != null) {
            membership.setAccessHoursStart(request.getAccessHoursStart());
        }
        
        if (request.getAccessHoursEnd() != null) {
            membership.setAccessHoursEnd(request.getAccessHoursEnd());
        }
        
        if (request.getGuestPassesIncluded() != null) {
            membership.setGuestPassesIncluded(request.getGuestPassesIncluded());
        }
        
        if (request.getGuestPassesUsed() != null) {
            membership.setGuestPassesUsed(request.getGuestPassesUsed());
        }
        
        if (request.getPersonalTrainingSessions() != null) {
            membership.setPersonalTrainingSessions(request.getPersonalTrainingSessions());
        }
        
        if (request.getGroupClassesIncluded() != null) {
            membership.setGroupClassesIncluded(request.getGroupClassesIncluded());
        }
        
        if (request.getLockerRentalIncluded() != null) {
            membership.setLockerRentalIncluded(request.getLockerRentalIncluded());
        }
        
        Membership updatedMembership = membershipRepository.save(membership);
        return convertToResponse(updatedMembership);
    }
    
    public void deleteMembership(Long id) {
        if (!membershipRepository.existsById(id)) {
            throw new RuntimeException("Membership not found");
        }
        membershipRepository.deleteById(id);
    }
    
    private MembershipDto.Response convertToResponse(Membership membership) {
        MembershipDto.Response response = new MembershipDto.Response();
        response.setId(membership.getId());
        response.setMemberId(membership.getMember().getId());
        response.setMembershipType(membership.getMembershipType());
        response.setDescription(membership.getDescription());
        response.setPrice(membership.getPrice());
        response.setDurationMonths(membership.getDurationMonths());
        response.setStartDate(membership.getStartDate());
        response.setEndDate(membership.getEndDate());
        response.setAutoRenewal(membership.getAutoRenewal());
        response.setStatus(membership.getStatus());
        response.setAccessHoursStart(membership.getAccessHoursStart());
        response.setAccessHoursEnd(membership.getAccessHoursEnd());
        response.setGuestPassesIncluded(membership.getGuestPassesIncluded());
        response.setGuestPassesUsed(membership.getGuestPassesUsed());
        response.setPersonalTrainingSessions(membership.getPersonalTrainingSessions());
        response.setGroupClassesIncluded(membership.getGroupClassesIncluded());
        response.setLockerRentalIncluded(membership.getLockerRentalIncluded());
        response.setCreatedAt(membership.getCreatedAt());
        response.setUpdatedAt(membership.getUpdatedAt());
        
        // Member information
        response.setMemberName(membership.getMember().getUser().getFirstName() + " " + membership.getMember().getUser().getLastName());
        response.setMemberNumber(membership.getMember().getMemberNumber());
        
        return response;
    }
}
