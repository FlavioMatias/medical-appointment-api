package medical.api.appointment.dto.doctor;

public record DoctorResponseDTO(
        Long id,
        String name,
        String licenseNumber,
        String specialty,
        String email,
        String phoneNumber
) {
}
