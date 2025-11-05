package medical.api.appointment.dto.appointment;

import java.time.LocalDateTime;

public record AppointmentFilterDTO(
        Long patientId,
        Long doctorId,
        String room,
        LocalDateTime dateTime
) {
}
