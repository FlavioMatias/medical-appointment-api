package medical.api.appointment.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class DoctorTests {

    @Test
    @DisplayName("Should throw exception when licenseNumber is null")
    void shouldThrowExceptionWhenLicenseNumberIsNull() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. House");
        doctor.setLicenseNumber(null);

        assertThatThrownBy(doctor::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("License number");
    }

    @Test
    @DisplayName("Should throw exception when licenseNumber is invalid")
    void shouldThrowExceptionWhenLicenseNumberIsInvalid() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Wilson");
        doctor.setLicenseNumber("12345"); // missing /UF

        assertThatThrownBy(doctor::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("License number");
    }

    @Test
    @DisplayName("Should throw exception when email is invalid")
    void shouldThrowExceptionWhenEmailIsInvalid() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Cameron");
        doctor.setLicenseNumber("1234/SP");
        doctor.setEmail("invalid_email");

        assertThatThrownBy(doctor::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid email");
    }

    @Test
    @DisplayName("Should throw exception when phone number is invalid")
    void shouldThrowExceptionWhenPhoneNumberIsInvalid() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Foreman");
        doctor.setLicenseNumber("1234/SP");
        doctor.setPhoneNumber("123");

        assertThatThrownBy(doctor::validate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Phone number");
    }

    @Test
    @DisplayName("Should pass validation when all fields are valid")
    void shouldPassValidationWhenAllFieldsAreValid() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Chase");
        doctor.setLicenseNumber("12345/RJ");
        doctor.setEmail("chase@hospital.com");
        doctor.setPhoneNumber("+5511987654321");

        assertThatCode(doctor::validate)
                .doesNotThrowAnyException();
    }
}
