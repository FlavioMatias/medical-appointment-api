package medical.api.appointment.dto.patient;

import medical.api.appointment.common.enums.InsurancePlan;

public record PatientRequestDTO(
        String name,
        String cpf,
        InsurancePlan insurancePlan
) {}

