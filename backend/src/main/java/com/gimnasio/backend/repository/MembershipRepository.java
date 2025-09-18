package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    
    List<Membership> findByMemberId(Long memberId);
    
    List<Membership> findByMembershipType(String membershipType);
    
    List<Membership> findByStatus(Membership.MembershipStatus status);
    
    List<Membership> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Membership> findByEndDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT m FROM Membership m WHERE m.member.id = :memberId AND m.status = 'ACTIVE'")
    List<Membership> findActiveMembershipsByMember(@Param("memberId") Long memberId);
    
    @Query("SELECT m FROM Membership m WHERE m.endDate <= :date AND m.status = 'ACTIVE'")
    List<Membership> findExpiredMemberships(@Param("date") LocalDate date);
    
    @Query("SELECT m FROM Membership m WHERE m.endDate <= :date AND m.autoRenewal = true")
    List<Membership> findMembershipsForAutoRenewal(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(m) FROM Membership m WHERE m.status = :status")
    long countByStatus(@Param("status") Membership.MembershipStatus status);
    
    @Query("SELECT COUNT(m) FROM Membership m WHERE m.membershipType = :type")
    long countByMembershipType(@Param("type") String membershipType);
}
