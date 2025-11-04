package medical.api.appointment.service;

import medical.api.appointment.common.factory.PatientFactory;
import medical.api.appointment.dto.PatientFilterDTO;
import medical.api.appointment.dto.PatientRequestDTO;
import medical.api.appointment.dto.PatientResponseDTO;
import medical.api.appointment.mapper.PatientMapper;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;
import medical.api.appointment.repository.PatientRepository;
import medical.api.appointment.repository.UserRepository;
import medical.api.appointment.specfications.PatientSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepo;
    private final UserRepository userRepo;

    public PatientService(PatientRepository patientRepo, UserRepository userRepo){
        this.patientRepo = patientRepo;
        this.userRepo = userRepo;
    }

    public List<PatientResponseDTO> findPatients(PatientFilterDTO filter, Long schedulerId) {
        Specification<Patient> spec = PatientSpecifications.byFilters(filter, schedulerId);
        return patientRepo.findAll(spec).stream()
                .map(PatientMapper::toResponse)
                .toList();
    }

    public PatientResponseDTO findById(Long id){
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("patient not found"));
        return PatientMapper.toResponse(patient);
    }

    @Transactional
    public void deleteById(Long id){ patientRepo.deleteById(id); }

    @Transactional
    public PatientResponseDTO create(PatientRequestDTO dto, Long userId){
        User scheduler =  userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Scheduler not found"));

        Patient newPatient = PatientFactory.create(dto, scheduler);

        return PatientMapper.toResponse(
                patientRepo.save(newPatient)
        );
    }

    @Transactional
    public PatientResponseDTO update(Long id,PatientRequestDTO dto){
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        PatientMapper.updateEntity(patient, dto);

        return PatientMapper.toResponse(
                patientRepo.save(patient)
        );
    }
}
