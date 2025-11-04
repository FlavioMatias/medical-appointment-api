package medical.api.appointment.dto;

import medical.api.appointment.common.enums.InsurancePlan;

public record PatientResponseDTO(
        Long id,
        String name,
        String cpf,
        InsurancePlan insurancePlan
) { }

