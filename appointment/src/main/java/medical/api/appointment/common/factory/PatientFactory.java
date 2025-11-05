package medical.api.appointment.common.factory;


import medical.api.appointment.common.enums.PatientStatus;
import medical.api.appointment.dto.patient.PatientRequestDTO;
import medical.api.appointment.model.Patient;
import medical.api.appointment.model.User;

public class PatientFactory {

    private PatientFactory() {}

    public static Patient create(PatientRequestDTO dto, User scheduler) {
        if (dto == null) {
            throw new IllegalArgumentException("PatientRequestDTO cannot be null");
        }
        if (scheduler == null) {
            throw new IllegalArgumentException("Scheduler cannot be null");
        }

        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setCpf(dto.cpf());
        patient.setInsurancePlan(dto.insurancePlan());
        patient.setScheduler(scheduler);
        patient.setStatus(PatientStatus.ACTIVE);

        return patient;
    }
}