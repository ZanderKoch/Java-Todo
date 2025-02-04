package model;

public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(int id, String firstName, String lastName, String email) {
        if (firstName == null){
            throw new IllegalArgumentException("firstName may not be null");
        }
        if (lastName == null){
            throw new IllegalArgumentException("lastName may not be null");
        }
        if (email == null){
            throw new IllegalArgumentException("email may not be null");
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
        if (firstName == null){
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
        if (email == null){
            throw new IllegalArgumentException("email may not be null");
        }
        this.email = email;
    }

    public String getSummary() {
        return String.format("{id: %d, name: %s, email: %s}",
                id, getFullName(), email);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
