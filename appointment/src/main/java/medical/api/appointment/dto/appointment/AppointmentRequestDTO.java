package medical.api.appointment.dto.appointment;

import medical.api.appointment.common.enums.AppointmentType;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        Long patientId,
        Long doctorId,
        String room,
        LocalDateTime dateTime,
        AppointmentType type

) { }
