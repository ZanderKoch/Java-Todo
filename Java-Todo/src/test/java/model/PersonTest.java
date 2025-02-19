package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;
    private AppUser user;

    @BeforeEach
    void setUp() {
        user = new AppUser("NOlsson", "password123", AppRole.ROLE_APP_USER);
        person = new Person( "Nisse", "Olsson", "nisse@gmail.com", user);
    }

    @Test
    void testToString() {
        String result = person.toString();
        String expected = String.format("{id: %d, name: Nisse Olsson, email: nisse@gmail.com}", person.getId());
        assertEquals(expected, result);
    }

    @Test
    void ConstructorThrowsExceptionWhenFirstNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person( null, "Olsson", "nisse@gmail.com", user),
                "Expected IllegalArgumentException for null firstName"
        );
    }

    @Test
    void ConstructorThrowsExceptionWhenLastNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person( "Nisse", null, "nisse@gmail.com", user),
                "Expected IllegalArgumentException for null lastName"
        );
    }

    @Test
    void ConstructorThrowsExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Person( "Nisse", "Olsson", null, user),
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

    @Test
    void testEqualsSameProperties() {
        AppUser otherUser = new AppUser("NOlsson", "password123", AppRole.ROLE_APP_USER);
        Person otherPerson = new Person(person.getId(), "Nisse", "Olsson", "nisse@gmail.com", otherUser);


        assertEquals(person, otherPerson, "Persons with the same properties should be equal.");
    }

    @Test
    void testNotEqualsDifferentId() {
        Person otherPerson = new Person( "Nisse", "Olsson", "nisse@gmail.com", user);

        assertNotEquals(person, otherPerson, "Persons with different IDs should not be equal.");
    }

    @Test
    void testEqualsHashCodeContract() {
        AppUser otherUser = new AppUser("NOlsson", "password123", AppRole.ROLE_APP_USER);
        Person otherPerson = new Person(person.getId(), "Nisse", "Olsson", "nisse@gmail.com", otherUser);

        assertEquals(person.hashCode(), otherPerson.hashCode(), "Equal objects must have the same hash code.");
    }

    @Test
    void testHashCode_NotEquals() {
        Person otherPerson = new Person( "Nisse", "Olsson", "nisse@gmail.com", user);

        assertNotEquals(person.hashCode(), otherPerson.hashCode(), "Different objects should ideally have different hash codes.");
    }
}
