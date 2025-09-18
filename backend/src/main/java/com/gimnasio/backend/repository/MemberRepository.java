package com.gimnasio.backend.repository;

import com.gimnasio.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    Optional<Member> findByMemberNumber(String memberNumber);
    
    Optional<Member> findByUserId(Long userId);
    
    boolean existsByMemberNumber(String memberNumber);
    
    List<Member> findByStatus(Member.MembershipStatus status);
    
    List<Member> findByJoinDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT m FROM Member m WHERE m.user.firstName LIKE %:name% OR m.user.lastName LIKE %:name%")
    List<Member> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT m FROM Member m WHERE m.status = :status AND m.joinDate >= :fromDate")
    List<Member> findActiveMembersSince(@Param("status") Member.MembershipStatus status, @Param("fromDate") LocalDate fromDate);
    
    @Query("SELECT COUNT(m) FROM Member m WHERE m.status = :status")
    long countByStatus(@Param("status") Member.MembershipStatus status);
    
    @Query("SELECT m FROM Member m WHERE m.user.email = :email")
    Optional<Member> findByUserEmail(@Param("email") String email);
}

