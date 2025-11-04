package medical.api.appointment.model;

import medical.api.appointment.common.enums.AppointmentType;
import medical.api.appointment.common.enums.InsurancePlan;
import medical.api.appointment.common.enums.PatientStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class AppointmentTests {

    private User scheduler;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setup() {
        scheduler = new User(1L, "echo", "securePass");
        doctor = new Doctor(
                1L,
                "Dr. House",
                "1234/SP",
                "Cardiology",
                "house@hospital.com",
                "+5511987654321",
                scheduler,
                new ArrayList<>() // lista vazia de appointments
        );
        patient = new Patient(1L, "John Doe", "12345678901", InsurancePlan.BASIC, PatientStatus.ACTIVE, scheduler);
    }

    @Test
    @DisplayName("Should create Appointment using all-args constructor correctly")
    void shouldCreateAppointmentWithAllArgsConstructor() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 1, 1, 14, 0);
        Appointment appointment = new Appointment(1L, dateTime,  doctor, "Room 12", patient, AppointmentType.ROUTINE, scheduler);

        assertThat(appointment.getId()).isEqualTo(1L);
        assertThat(appointment.getDateTime()).isEqualTo(dateTime);
        assertThat(appointment.getType()).isEqualTo(AppointmentType.ROUTINE);
        assertThat(appointment.getDoctor()).isEqualTo(doctor);
        assertThat(appointment.getRoom()).isEqualTo("Room 12");
        assertThat(appointment.getPatient()).isEqualTo(patient);
        assertThat(appointment.getScheduler()).isEqualTo(scheduler);
    }

    @Test
    @DisplayName("Should set and get fields correctly")
    void shouldSetAndGetFieldsCorrectly() {
        Appointment appointment = new Appointment();
        LocalDateTime dateTime = LocalDateTime.of(2025, 5, 10, 9, 30);

        appointment.setId(10L);
        appointment.setDateTime(dateTime);
        appointment.setType(AppointmentType.FOLLOW_UP);
        appointment.setDoctor(doctor);
        appointment.setRoom("Room B");
        appointment.setPatient(patient);
        appointment.setScheduler(scheduler);

        assertThat(appointment.getId()).isEqualTo(10L);
        assertThat(appointment.getDateTime()).isEqualTo(dateTime);
        assertThat(appointment.getType()).isEqualTo(AppointmentType.FOLLOW_UP);
        assertThat(appointment.getDoctor()).isEqualTo(doctor);
        assertThat(appointment.getRoom()).isEqualTo("Room B");
        assertThat(appointment.getPatient()).isEqualTo(patient);
        assertThat(appointment.getScheduler()).isEqualTo(scheduler);
    }

    @Test
    @DisplayName("Should consider two appointments equal when IDs match")
    void shouldConsiderAppointmentsEqualWhenIdsMatch() {
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();

        a1.setId(1L);
        a2.setId(1L);

        assertThat(a1).isEqualTo(a2);
        assertThat(a1.hashCode()).isEqualTo(a2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when IDs differ")
    void shouldNotBeEqualWhenIdsDiffer() {
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();

        a1.setId(1L);
        a2.setId(2L);

        assertThat(a1).isNotEqualTo(a2);
    }

    @Test
    @DisplayName("Should produce a meaningful toString containing room and type")
    void shouldProduceMeaningfulToString() {
        Appointment appointment = new Appointment();
        appointment.setRoom("Room 99");
        appointment.setType(AppointmentType.ROUTINE);

        String str = appointment.toString();

        assertThat(str).contains("Room 99");
        assertThat(str).contains("ROUTINE");
    }
}
