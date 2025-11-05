package medical.api.appointment.mapper;

import medical.api.appointment.dto.appointment.AppointmentRequestDTO;
import medical.api.appointment.dto.appointment.AppointmentResponseDTO;
import medical.api.appointment.model.Appointment;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentRequestDTO dto, Doctor doctor, Patient patient, User scheduler) {
        if (dto == null) return null;

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setScheduler(scheduler);
        appointment.setRoom(dto.room());
        appointment.setDateTime(dto.dateTime());
        appointment.setType(dto.type());

        return appointment;
    }

    public static AppointmentResponseDTO toResponse(Appointment appointment) {
        if (appointment == null) return null;

        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                appointment.getRoom(),
                appointment.getDateTime(),
                appointment.getType()
        );
    }

    public static void updateEntity(Appointment appointment, AppointmentRequestDTO dto, Doctor doctor, Patient patient) {
        if (appointment == null || dto == null) return;

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setRoom(dto.room());
        appointment.setDateTime(dto.dateTime());
        appointment.setType(dto.type());
    }
}
