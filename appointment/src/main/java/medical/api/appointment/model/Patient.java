package medical.api.appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import medical.api.appointment.enums.PatientStatus;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String insurancePlan;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;


    @PrePersist
    @PreUpdate
    protected void onCreate() {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF must contain exactly 11 digits");
        }

    }
}