package medical.api.appointment.specfications;

import jakarta.persistence.criteria.Predicate;
import medical.api.appointment.dto.appointment.AppointmentFilterDTO;
import medical.api.appointment.model.Appointment;
import org.springframework.data.jpa.domain.Specification;

public class AppointmentSpecifications {

    public static Specification<Appointment> byFilters(AppointmentFilterDTO filter, Long schedulerId) {
        return (root, query, cb) -> {
            Predicate p = cb.equal(root.get("scheduler").get("id"), schedulerId);

            if (filter.patientId() != null) {
                p = cb.and(p, cb.equal(root.get("patient").get("id"), filter.patientId()));
            }

            if (filter.doctorId() != null) {
                p = cb.and(p, cb.equal(root.get("doctor").get("id"), filter.doctorId()));
            }

            if (filter.room() != null && !filter.room().isBlank()) {
                p = cb.and(p, cb.equal(cb.lower(root.get("room")), filter.room().toLowerCase()));
            }

            if (filter.dateTime() != null) {
                p = cb.and(p, cb.equal(root.get("dateTime"), filter.dateTime()));
            }

            return p;
        };
    }
}
