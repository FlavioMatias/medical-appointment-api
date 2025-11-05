package medical.api.appointment.service;

import medical.api.appointment.common.factory.AppointmentFactory;
import medical.api.appointment.dto.appointment.AppointmentFilterDTO;
import medical.api.appointment.dto.appointment.AppointmentRequestDTO;
import medical.api.appointment.dto.appointment.AppointmentResponseDTO;
import medical.api.appointment.mapper.AppointmentMapper;
import medical.api.appointment.model.Appointment;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;
import medical.api.appointment.repository.AppointmentRepository;
import medical.api.appointment.repository.DoctorRepository;
import medical.api.appointment.repository.PatientRepository;
import medical.api.appointment.repository.UserRepository;
import medical.api.appointment.specfications.AppointmentSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    private final UserRepository userRepo;

    public AppointmentService(
            AppointmentRepository appointmentRepo,
            DoctorRepository doctorRepo,
            PatientRepository patientRepo,
            UserRepository userRepo
    ) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.userRepo = userRepo;
    }

    public List<AppointmentResponseDTO> findAppointments(AppointmentFilterDTO filter, Long schedulerId) {
        Specification<Appointment> spec = AppointmentSpecifications.byFilters(filter, schedulerId);
        return appointmentRepo.findAll(spec).stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    public AppointmentResponseDTO findById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return AppointmentMapper.toResponse(appointment);
    }

    @Transactional
    public AppointmentResponseDTO create(AppointmentRequestDTO dto, Long schedulerId) {
        User scheduler = userRepo.findById(schedulerId)
                .orElseThrow(() -> new RuntimeException("Scheduler not found"));

        Doctor doctor = doctorRepo.findById(dto.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepo.findById(dto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = AppointmentFactory.create(dto, doctor, patient, scheduler);

        return AppointmentMapper.toResponse(
                appointmentRepo.save(appointment)
        );
    }

    @Transactional
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO dto) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Doctor doctor = doctorRepo.findById(dto.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepo.findById(dto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        AppointmentMapper.updateEntity(appointment, dto, doctor, patient);

        return AppointmentMapper.toResponse(
                appointmentRepo.save(appointment)
        );
    }

    public void delete(Long id) {
        appointmentRepo.deleteById(id);
    }
}
