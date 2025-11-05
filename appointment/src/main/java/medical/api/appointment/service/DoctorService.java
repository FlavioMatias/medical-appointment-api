package medical.api.appointment.service;

import medical.api.appointment.dto.doctor.DoctorFilterDTO;
import medical.api.appointment.dto.doctor.DoctorResponseDTO;
import medical.api.appointment.mapper.DoctorMapper;
import medical.api.appointment.mapper.PatientMapper;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import medical.api.appointment.repository.DoctorRepository;
import medical.api.appointment.specfications.DoctorSpecifications;
import medical.api.appointment.specfications.PatientSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DoctorService {
    private final DoctorRepository doctorRepo;
    public DoctorService(DoctorRepository doctorRepo){
        this.doctorRepo = doctorRepo;
    }

    public List<DoctorResponseDTO> findDoctors(DoctorFilterDTO filter, Long schedulerId) {
        Specification<Doctor> spec = DoctorSpecifications.byFilters(filter, schedulerId);
        return doctorRepo.findAll(spec).stream()
                .map(DoctorMapper::toResponse)
                .toList();
    }
}
