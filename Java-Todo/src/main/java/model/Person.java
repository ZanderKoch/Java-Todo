package model;

import sequencers.PersonIdSequencer;

import java.util.Objects;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    /**
     * Constructs a new Person with an automatically assigned unique ID.
     * <p>
     * This constructor should be used in production code to ensure that each Person
     * instance gets a unique ID assigned by the {@code PersonIdSequencer}.
     * </p>
     *
     * @param firstName   The first name of the person (must not be null).
     * @param lastName    The last name of the person (must not be null).
     * @param email       The email address of the person (must not be null).
     * @param credentials The associated AppUser credentials (must not be null).
     * @throws IllegalArgumentException if any parameter is null.
     */
    public Person(String firstName, String lastName, String email, AppUser credentials) {
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

        this.id = PersonIdSequencer.getInstance().nextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Constructs a new Person with a manually assigned ID.
     * <p>
     * This constructor is intended <b>only for testing</b>, allowing explicit control
     * over the assigned ID. In normal use, the ID should be automatically generated
     * by {@code PersonIdSequencer}.
     * </p>
     *
     * @param id          The ID to assign to this Person.
     * @param firstName   The first name of the person (must not be null).
     * @param lastName    The last name of the person (must not be null).
     * @param email       The email address of the person (must not be null).
     * @param credentials The associated AppUser credentials (must not be null).
     * @throws IllegalArgumentException if any parameter is null.
     */
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
