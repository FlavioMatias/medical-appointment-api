package medical.api.appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import medical.api.appointment.enums.AppointmentType;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    @ManyToOne
    private Doctor doctor;

    private String room;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private User scheduler;
}