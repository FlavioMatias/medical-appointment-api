package medical.api.appointment.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class UserTests {

    @Test
    @DisplayName("Should create user correctly using all-args constructor")
    void shouldCreateUserWithAllArgsConstructor() {
        User user = new User(1L, "flavio", "123456");

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getUsername()).isEqualTo("flavio");
        assertThat(user.getPassword()).isEqualTo("123456");

    }

    @Test
    @DisplayName("Should allow setting and getting fields")
    void shouldSetAndGetFieldsCorrectly() {
        User user = new User();
        user.setId(10L);
        user.setUsername("echo");
        user.setPassword("securePass");

        assertThat(user.getId()).isEqualTo(10L);
        assertThat(user.getUsername()).isEqualTo("echo");
        assertThat(user.getPassword()).isEqualTo("securePass");
    }

    @Test
    @DisplayName("Should correctly evaluate equals and hashCode for identical users")
    void shouldEvaluateEqualsAndHashCodeCorrectly() {
        User user1 = new User(1L, "flavio", "123456");
        User user2 = new User(1L, "flavio", "123456");

        assertThat(user1).isEqualTo(user2);
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when users have different IDs")
    void shouldNotBeEqualWhenIdsDiffer() {
        User user1 = new User(1L, "flavio", "123456");
        User user2 = new User(2L, "flavio", "123456");

        assertThat(user1).isNotEqualTo(user2);
    }

}
