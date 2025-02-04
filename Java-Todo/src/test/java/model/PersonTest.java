package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {

    @Test
    void summary() {
        Person person = new Person(4, "Nisse", "Olsson", "nisse@gmail.com");
        String result = person.getSummary();

        assertEquals("{id: 4, name: Nisse Olsson, email: nisse@gmail.com}", result);
    }

    @Test
    void throwsExceptionWhenFirstNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person(1, null, "Olsson", "nisse@gmail.com"),
                "Expected IllegalArgumentException for null firstName"
        );
    }

    @Test
    void throwsExceptionWhenLastNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person(2, "Nisse", null, "nisse@gmail.com"),
                "Expected IllegalArgumentException for null lastName"
        );
    }

    @Test
    void throwsExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person(3, "Nisse", "Olsson", null),
                "Expected IllegalArgumentException for null email"
        );
    }
}
