package medical.api.appointment.specfications;

import jakarta.persistence.criteria.Predicate;
import medical.api.appointment.dto.doctor.DoctorFilterDTO;
import medical.api.appointment.dto.patient.PatientFilterDTO;
import medical.api.appointment.model.Doctor;
import medical.api.appointment.model.Patient;
import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecifications {
    public static Specification<Doctor> byFilters(DoctorFilterDTO filter, Long schedulerId) {
        return (root, query, cb) -> {
            Predicate p = cb.equal(root.get("scheduler").get("id"), schedulerId);

            if (filter.speciality() != null) {
                p = cb.and(p, cb.equal(root.get("speciality"), filter.speciality()));
            }
            if (filter.name() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
            }
            return p;
        };
    }
}
