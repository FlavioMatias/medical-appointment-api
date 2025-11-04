package medical.api.appointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    private String specialty;

    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @PrePersist
    @PreUpdate
    protected void validate(){
        if (licenseNumber == null || !licenseNumber.matches("\\d{4,6}/[A-Z]{2}")) {
            throw new IllegalArgumentException("License number must be 4-6 digits followed by / and 2 uppercase letters, e.g., 1234/SP");
        }

        if (email != null && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (phoneNumber != null && !phoneNumber.matches("^\\+?\\d{10,15}$")) {
            throw new IllegalArgumentException("Phone number must be 10-15 digits, can start with +");
        }
    }
}
