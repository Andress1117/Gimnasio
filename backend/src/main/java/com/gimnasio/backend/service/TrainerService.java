package com.gimnasio.backend.service;

import com.gimnasio.backend.dto.TrainerDto;
import com.gimnasio.backend.entity.Trainer;
import com.gimnasio.backend.entity.User;
import com.gimnasio.backend.repository.TrainerRepository;
import com.gimnasio.backend.repository.UserRepository;
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
public class TrainerService {
    
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    
    public TrainerDto.Response createTrainer(TrainerDto.CreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (trainerRepository.findByUserId(request.getUserId()).isPresent()) {
            throw new RuntimeException("Trainer already exists for this user");
        }
        
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setEmployeeId(generateEmployeeId());
        trainer.setSpecialization(request.getSpecialization());
        trainer.setCertifications(request.getCertifications());
        trainer.setExperienceYears(request.getExperienceYears());
        trainer.setHourlyRate(request.getHourlyRate());
        trainer.setBio(request.getBio());
        trainer.setMaxClientsPerDay(request.getMaxClientsPerDay() != null ? request.getMaxClientsPerDay() : 10);
        trainer.setCurrentClientsCount(0);
        trainer.setStatus(request.getStatus() != null ? request.getStatus() : Trainer.TrainerStatus.ACTIVE);
        trainer.setHireDate(LocalDateTime.now());
        
        Trainer savedTrainer = trainerRepository.save(trainer);
        return convertToResponse(savedTrainer);
    }
    
    @Transactional(readOnly = true)
    public List<TrainerDto.Response> getAllTrainers() {
        return trainerRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Optional<TrainerDto.Response> getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Optional<TrainerDto.Response> getTrainerByEmployeeId(String employeeId) {
        return trainerRepository.findByEmployeeId(employeeId)
                .map(this::convertToResponse);
    }
    
    @Transactional(readOnly = true)
    public Optional<TrainerDto.Response> getTrainerByUserId(Long userId) {
        return trainerRepository.findByUserId(userId)
                .map(this::convertToResponse);
    }
    
    public TrainerDto.Response updateTrainer(Long id, TrainerDto.UpdateRequest request) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        
        if (request.getSpecialization() != null) {
            trainer.setSpecialization(request.getSpecialization());
        }
        
        if (request.getCertifications() != null) {
            trainer.setCertifications(request.getCertifications());
        }
        
        if (request.getExperienceYears() != null) {
            trainer.setExperienceYears(request.getExperienceYears());
        }
        
        if (request.getHourlyRate() != null) {
            trainer.setHourlyRate(request.getHourlyRate());
        }
        
        if (request.getBio() != null) {
            trainer.setBio(request.getBio());
        }
        
        if (request.getMaxClientsPerDay() != null) {
            trainer.setMaxClientsPerDay(request.getMaxClientsPerDay());
        }
        
        if (request.getCurrentClientsCount() != null) {
            trainer.setCurrentClientsCount(request.getCurrentClientsCount());
        }
        
        if (request.getStatus() != null) {
            trainer.setStatus(request.getStatus());
        }
        
        Trainer updatedTrainer = trainerRepository.save(trainer);
        return convertToResponse(updatedTrainer);
    }
    
    public void deleteTrainer(Long id) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer not found");
        }
        trainerRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<TrainerDto.Response> getTrainersByStatus(Trainer.TrainerStatus status) {
        return trainerRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TrainerDto.Response> getAvailableTrainers() {
        return trainerRepository.findAvailableTrainers().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TrainerDto.Response> searchTrainersByName(String name) {
        return trainerRepository.findByNameContaining(name).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TrainerDto.SummaryResponse> getTrainerSummaries() {
        return trainerRepository.findAll().stream()
                .map(this::convertToSummaryResponse)
                .collect(Collectors.toList());
    }
    
    private String generateEmployeeId() {
        String prefix = "TRN";
        long count = trainerRepository.count() + 1;
        return prefix + String.format("%06d", count);
    }
    
    private TrainerDto.Response convertToResponse(Trainer trainer) {
        TrainerDto.Response response = new TrainerDto.Response();
        response.setId(trainer.getId());
        response.setUserId(trainer.getUser().getId());
        response.setEmployeeId(trainer.getEmployeeId());
        response.setSpecialization(trainer.getSpecialization());
        response.setCertifications(trainer.getCertifications());
        response.setExperienceYears(trainer.getExperienceYears());
        response.setHourlyRate(trainer.getHourlyRate());
        response.setBio(trainer.getBio());
        response.setMaxClientsPerDay(trainer.getMaxClientsPerDay());
        response.setCurrentClientsCount(trainer.getCurrentClientsCount());
        response.setStatus(trainer.getStatus());
        response.setHireDate(trainer.getHireDate());
        response.setCreatedAt(trainer.getCreatedAt());
        response.setUpdatedAt(trainer.getUpdatedAt());
        
        // User information
        response.setFirstName(trainer.getUser().getFirstName());
        response.setLastName(trainer.getUser().getLastName());
        response.setEmail(trainer.getUser().getEmail());
        response.setPhoneNumber(trainer.getUser().getPhoneNumber());
        
        return response;
    }
    
    private TrainerDto.SummaryResponse convertToSummaryResponse(Trainer trainer) {
        TrainerDto.SummaryResponse response = new TrainerDto.SummaryResponse();
        response.setId(trainer.getId());
        response.setEmployeeId(trainer.getEmployeeId());
        response.setFirstName(trainer.getUser().getFirstName());
        response.setLastName(trainer.getUser().getLastName());
        response.setSpecialization(trainer.getSpecialization());
        response.setStatus(trainer.getStatus());
        response.setCurrentClientsCount(trainer.getCurrentClientsCount());
        response.setMaxClientsPerDay(trainer.getMaxClientsPerDay());
        return response;
    }
}

