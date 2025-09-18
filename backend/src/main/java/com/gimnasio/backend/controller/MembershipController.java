package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.MembershipDto;
import com.gimnasio.backend.entity.Membership;
import com.gimnasio.backend.repository.MembershipRepository;
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
    
    private final MembershipRepository membershipRepository;
    
    @PostMapping
    public ResponseEntity<MembershipDto.Response> createMembership(@Valid @RequestBody MembershipDto.CreateRequest request) {
        // Implementation would go here
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<MembershipDto.Response>> getAllMemberships() {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MembershipDto.Response> getMembershipById(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MembershipDto.Response> updateMembership(@PathVariable Long id, @Valid @RequestBody MembershipDto.UpdateRequest request) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.noContent().build();
    }
}
