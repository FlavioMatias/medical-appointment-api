package medical.api.appointment.service;

import medical.api.appointment.common.factory.DoctorFactory;
import medical.api.appointment.dto.doctor.DoctorFilterDTO;
import medical.api.appointment.dto.doctor.DoctorRequestDTO;
import medical.api.appointment.dto.doctor.DoctorResponseDTO;
import medical.api.appointment.dto.patient.PatientRequestDTO;
import medical.api.appointment.dto.patient.PatientResponseDTO;
import medical.api.appointment.mapper.DoctorMapper;
import medical.api.appointment.mapper.PatientMapper;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;
import medical.api.appointment.repository.DoctorRepository;
import medical.api.appointment.repository.UserRepository;
import medical.api.appointment.specfications.DoctorSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepo;
    private final UserRepository userRepo;

    public DoctorService(DoctorRepository doctorRepo, UserRepository userRepo){
        this.doctorRepo = doctorRepo;
        this.userRepo = userRepo;
    }

    public List<DoctorResponseDTO> findDoctors(DoctorFilterDTO filter, Long schedulerId) {
        Specification<Doctor> spec = DoctorSpecifications.byFilters(filter, schedulerId);
        return doctorRepo.findAll(spec).stream()
                .map(DoctorMapper::toResponse)
                .toList();
    }

    public DoctorResponseDTO findById(Long id){
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return DoctorMapper.toResponse(doctor);
    }

    @Transactional
    public DoctorResponseDTO create(DoctorRequestDTO dto, Long userId ){
        User scheduler =  userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Scheduler not found"));
        Doctor doctor = DoctorFactory.create(dto, scheduler);

        return DoctorMapper.toResponse(
                doctorRepo.save(doctor)
        );
    }

    @Transactional
    public DoctorResponseDTO update(Long id, DoctorRequestDTO dto){
        Doctor doctor = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        DoctorMapper.updateEntity(doctor, dto);
        return DoctorMapper.toResponse(
                doctorRepo.save(doctor)
        );
    }

    @Transactional
    public void delete(Long id){
        doctorRepo.deleteById(id);
    }
}
