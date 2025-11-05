package medical.api.appointment.dto.doctor;

import jakarta.persistence.Column;

public record DoctorRequestDTO(
        String name,
        String licenseNumber,
        String specialty,
        String email,
        String phoneNumber
) { }
