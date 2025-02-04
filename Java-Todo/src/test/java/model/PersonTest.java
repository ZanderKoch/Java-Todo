package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(4, "Nisse", "Olsson", "nisse@gmail.com");
    }

    @Test
    void summary() {
        String result = person.getSummary();

        assertEquals("{id: 4, name: Nisse Olsson, email: nisse@gmail.com}", result);
    }

    @Test
    void ConstructorThrowsExceptionWhenFirstNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person(1, null, "Olsson", "nisse@gmail.com"),
                "Expected IllegalArgumentException for null firstName"
        );
    }

    @Test
    void ConstructorThrowsExceptionWhenLastNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person(2, "Nisse", null, "nisse@gmail.com"),
                "Expected IllegalArgumentException for null lastName"
        );
    }

    @Test
    void ConstructorThrowsExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person(3, "Nisse", "Olsson", null),
                "Expected IllegalArgumentException for null email"
        );
    }

    @Test
    void setFirstNameToValidValue() {
        person.setFirstName("NewName");
        assertEquals("NewName", person.getFirstName(), "First name should be updated correctly.");
    }

    @Test
    void throwsExceptionWhenSettingFirstNameToNull() {
        assertThrows(IllegalArgumentException.class,
                () -> person.setFirstName(null),
                "Expected IllegalArgumentException when setting firstName to null."
        );
    }

    @Test
    void setLastNameToValidValue() {
        person.setLastName("NewLastName");
        assertEquals("NewLastName", person.getLastName(), "Last name should be updated correctly.");
    }

    @Test
    void throwsExceptionWhenSettingLastNameToNull() {
        assertThrows(IllegalArgumentException.class,
                () -> person.setLastName(null),
                "Expected IllegalArgumentException when setting lastName to null."
        );
    }

    @Test
    void setEmailToValidValue() {
        person.setEmail("new.email@gmail.com");
        assertEquals("new.email@gmail.com", person.getEmail(), "Email should be updated correctly.");
    }

    @Test
    void throwsExceptionWhenSettingEmailToNull() {
        assertThrows(IllegalArgumentException.class,
                () -> person.setEmail(null),
                "Expected IllegalArgumentException when setting email to null."
        );
    }
}
