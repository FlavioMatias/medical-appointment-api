package medical.api.appointment.mapper;

import medical.api.appointment.dto.doctor.DoctorRequestDTO;
import medical.api.appointment.dto.doctor.DoctorResponseDTO;
import medical.api.appointment.dto.patient.PatientRequestDTO;
import medical.api.appointment.dto.patient.PatientResponseDTO;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;

public class DoctorMapper {

    // Entity -> ResponseDTO
    public static DoctorResponseDTO toResponse(Doctor doctor) {
        if (doctor == null) return null;

        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getLicenseNumber(),
                doctor.getSpecialty(),
                doctor.getEmail(),
                doctor.getPhoneNumber()
        );
    }

    public static void updateEntity(Doctor doctor, DoctorRequestDTO dto) {
        if (doctor == null || dto == null) return;

        doctor.setName(dto.name());
        doctor.setEmail(dto.email());
        doctor.setLicenseNumber(dto.licenseNumber());
        doctor.setSpecialty(dto.specialty());
        doctor.setPhoneNumber(dto.phoneNumber());
    }
}