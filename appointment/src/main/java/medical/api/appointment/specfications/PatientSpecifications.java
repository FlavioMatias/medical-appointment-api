package medical.api.appointment.specfications;

import jakarta.persistence.criteria.Predicate;
import medical.api.appointment.dto.PatientFilterDTO;
import medical.api.appointment.model.Patient;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecifications {
    public static Specification<Patient> byFilters(PatientFilterDTO filter, Long schedulerId) {
        return (root, query, cb) -> {
            Predicate p = cb.equal(root.get("scheduler").get("id"), schedulerId);

            if (filter.insurancePlan() != null) {
                p = cb.and(p, cb.equal(root.get("insurancePlan"), filter.insurancePlan()));
            }
            if (filter.name() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }
            return p;
        };
    }
}
