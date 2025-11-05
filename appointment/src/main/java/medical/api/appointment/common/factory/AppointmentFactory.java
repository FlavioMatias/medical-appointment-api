package medical.api.appointment.common.factory;

import medical.api.appointment.dto.appointment.AppointmentRequestDTO;
import medical.api.appointment.model.Appointment;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;

public class AppointmentFactory {

    private AppointmentFactory() {}

    public static Appointment create(AppointmentRequestDTO dto, Doctor doctor, Patient patient, User scheduler) {
        if (dto == null) {
            throw new IllegalArgumentException("AppointmentRequestDTO cannot be null");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        if (scheduler == null) {
            throw new IllegalArgumentException("Scheduler cannot be null");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setScheduler(scheduler);
        appointment.setRoom(dto.room());
        appointment.setDateTime(dto.dateTime());
        appointment.setType(dto.type());

        return appointment;
    }
}
