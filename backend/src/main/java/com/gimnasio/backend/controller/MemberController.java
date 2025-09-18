package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.MemberDto;
import com.gimnasio.backend.entity.Member;
import com.gimnasio.backend.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {
    
    private final MemberService memberService;
    
    @PostMapping
    public ResponseEntity<MemberDto.Response> createMember(@Valid @RequestBody MemberDto.CreateRequest request) {
        try {
            MemberDto.Response response = memberService.createMember(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<MemberDto.Response>> getAllMembers() {
        List<MemberDto.Response> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
    
    @GetMapping("/summaries")
    public ResponseEntity<List<MemberDto.SummaryResponse>> getMemberSummaries() {
        List<MemberDto.SummaryResponse> summaries = memberService.getMemberSummaries();
        return ResponseEntity.ok(summaries);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto.Response> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(member -> ResponseEntity.ok(member))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/member-number/{memberNumber}")
    public ResponseEntity<MemberDto.Response> getMemberByMemberNumber(@PathVariable String memberNumber) {
        return memberService.getMemberByMemberNumber(memberNumber)
                .map(member -> ResponseEntity.ok(member))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<MemberDto.Response> getMemberByUserId(@PathVariable Long userId) {
        return memberService.getMemberByUserId(userId)
                .map(member -> ResponseEntity.ok(member))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MemberDto.Response> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDto.UpdateRequest request) {
        try {
            MemberDto.Response response = memberService.updateMember(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<MemberDto.Response>> getMembersByStatus(@PathVariable Member.MembershipStatus status) {
        List<MemberDto.Response> members = memberService.getMembersByStatus(status);
        return ResponseEntity.ok(members);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<MemberDto.Response>> searchMembersByName(@RequestParam String name) {
        List<MemberDto.Response> members = memberService.searchMembersByName(name);
        return ResponseEntity.ok(members);
    }
}
