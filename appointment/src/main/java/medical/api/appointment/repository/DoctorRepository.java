package medical.api.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import medical.api.appointment.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {}