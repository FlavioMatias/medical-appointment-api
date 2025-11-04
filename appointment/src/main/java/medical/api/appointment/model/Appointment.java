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

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private String room;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Patient patient;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentType type;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User scheduler;
}