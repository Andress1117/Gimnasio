package com.gimnasio.backend.controller;

import com.gimnasio.backend.dto.PaymentDto;
import com.gimnasio.backend.entity.Payment;
import com.gimnasio.backend.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {
    
    private final PaymentRepository paymentRepository;
    
    @PostMapping
    public ResponseEntity<PaymentDto.Response> createPayment(@Valid @RequestBody PaymentDto.CreateRequest request) {
        // Implementation would go here
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<PaymentDto.Response>> getAllPayments() {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto.Response> getPaymentById(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto.Response> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDto.UpdateRequest request) {
        // Implementation would go here
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        // Implementation would go here
        return ResponseEntity.noContent().build();
    }
}

