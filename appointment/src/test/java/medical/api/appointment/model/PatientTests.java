package medical.api.appointment.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PatientTests {

    @Test
    @DisplayName("Should throw exception when CPF is null")
    void shouldThrowExceptionWhenCpfIsNull() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setCpf(null);

        assertThatThrownBy(patient::onCreate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CPF must contain exactly 11 digits");
    }

    @Test
    @DisplayName("Should throw exception when CPF has less than 11 digits")
    void shouldThrowExceptionWhenCpfHasLessThan11Digits() {
        Patient patient = new Patient();
        patient.setName("Jane Doe");
        patient.setCpf("1234567890"); // 10 digits

        assertThatThrownBy(patient::onCreate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CPF must contain exactly 11 digits");
    }

    @Test
    @DisplayName("Should throw exception when CPF has more than 11 digits")
    void shouldThrowExceptionWhenCpfHasMoreThan11Digits() {
        Patient patient = new Patient();
        patient.setName("Alex Smith");
        patient.setCpf("123456789012"); // 12 digits

        assertThatThrownBy(patient::onCreate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CPF must contain exactly 11 digits");
    }

    @Test
    @DisplayName("Should throw exception when CPF contains letters")
    void shouldThrowExceptionWhenCpfContainsLetters() {
        Patient patient = new Patient();
        patient.setName("Maria Silva");
        patient.setCpf("12345A78901"); // contains a letter

        assertThatThrownBy(patient::onCreate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CPF must contain exactly 11 digits");
    }

    @Test
    @DisplayName("Should pass validation when CPF has exactly 11 digits")
    void shouldPassValidationWhenCpfIsValid() {
        Patient patient = new Patient();
        patient.setName("Carlos Souza");
        patient.setCpf("12345678901");
        patient.setInsurancePlan("Premium");

        assertThatCode(patient::onCreate)
                .doesNotThrowAnyException();
    }
}
