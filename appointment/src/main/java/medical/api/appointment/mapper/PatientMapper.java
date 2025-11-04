package medical.api.appointment.mapper;

import medical.api.appointment.dto.PatientRequestDTO;
import medical.api.appointment.dto.PatientResponseDTO;
import medical.api.appointment.model.Patient;

public class PatientMapper {
    // RequestDTO -> Entity
    public static Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) return null;

        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setCpf(dto.cpf());
        patient.setInsurancePlan(dto.insurancePlan());
        return patient;
    }

    // Entity -> ResponseDTO
    public static PatientResponseDTO toResponse(Patient patient) {
        if (patient == null) return null;

        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getInsurancePlan()
        );
    }

    public static void updateEntity(Patient patient, PatientRequestDTO dto) {
        if (patient == null || dto == null) return;

        patient.setName(dto.name());
        patient.setCpf(dto.cpf());
        patient.setInsurancePlan(dto.insurancePlan());
    }
}
