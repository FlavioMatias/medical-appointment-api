package medical.api.appointment.dto.patient;

import medical.api.appointment.common.enums.InsurancePlan;

public record PatientFilterDTO (
    InsurancePlan insurancePlan,
    String name
) {}