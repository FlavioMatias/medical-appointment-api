package medical.api.appointment.common.factory;

import medical.api.appointment.common.enums.PatientStatus;
import medical.api.appointment.dto.doctor.DoctorRequestDTO;
import medical.api.appointment.dto.patient.PatientRequestDTO;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;

import javax.print.Doc;

public class DoctorFactory {
    private DoctorFactory() {}

    public static Doctor create(DoctorRequestDTO dto, User scheduler) {
        if (dto == null) {
            throw new IllegalArgumentException("DoctorRequestDTO cannot be null");
        }
        if (scheduler == null) {
            throw new IllegalArgumentException("Scheduler cannot be null");
        }

        Doctor doctor = new Doctor();
        doctor.setName(dto.name());
        doctor.setEmail(dto.email());
        doctor.setPhoneNumber(dto.phoneNumber());
        doctor.setSpecialty(dto.specialty());
        doctor.setLicenseNumber(dto.licenseNumber());
        doctor.setScheduler(scheduler);

        return doctor;
    }
}
