package medical.api.appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import medical.api.appointment.common.enums.InsurancePlan;
import medical.api.appointment.common.enums.PatientStatus;


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

    @Enumerated(EnumType.STRING)
    private InsurancePlan insurancePlan;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    @ManyToOne
    private User scheduler;



    @PrePersist
    @PreUpdate
    protected void onCreate() {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF must contain exactly 11 digits");
        }

    }
}