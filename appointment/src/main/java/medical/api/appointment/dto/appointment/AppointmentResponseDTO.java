package medical.api.appointment.dto.appointment;

import medical.api.appointment.common.enums.AppointmentType;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        Long patientId,
        Long doctorId,
        String room,
        LocalDateTime dateTime,
        AppointmentType typ
) {
}
