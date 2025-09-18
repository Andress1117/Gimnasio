package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.MembershipDto;
import com.gimnasio.backend.entity.Membership;
import com.gimnasio.backend.service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MembershipController {
    
    private final MembershipService membershipService;
    
    @PostMapping
    public ResponseEntity<MembershipDto.Response> createMembership(@Valid @RequestBody MembershipDto.CreateRequest request) {
        try {
            MembershipDto.Response response = membershipService.createMembership(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<MembershipDto.Response>> getAllMemberships() {
        List<MembershipDto.Response> memberships = membershipService.getAllMemberships();
        return ResponseEntity.ok(memberships);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MembershipDto.Response> getMembershipById(@PathVariable Long id) {
        return membershipService.getMembershipById(id)
                .map(membership -> ResponseEntity.ok(membership))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MembershipDto.Response> updateMembership(@PathVariable Long id, @Valid @RequestBody MembershipDto.UpdateRequest request) {
        try {
            MembershipDto.Response response = membershipService.updateMembership(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        try {
            membershipService.deleteMembership(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
