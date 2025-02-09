package model;

import java.util.Objects;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    public Person(int id, String firstName, String lastName, String email, AppUser credentials) {
        if (firstName == null) {
            throw new IllegalArgumentException("firstName may not be null");
        }
        if (lastName == null) {
            throw new IllegalArgumentException("lastName may not be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("email may not be null");
        }
        if (credentials == null) {
            throw new IllegalArgumentException("credentials may not be null");
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("firstName may not be null");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("lastName may not be null");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email may not be null");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, name: %s, email: %s}",
                id, getFullName(), email);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
