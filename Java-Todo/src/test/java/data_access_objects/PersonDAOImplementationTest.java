package data_access_objects;

import model.Person;
import model.AppUser;
import model.AppRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplementationTest {
    private PersonDAOImplementation personDAO;
    private Person testPerson1;
    private Person testPerson2;

    @BeforeEach
    void setup() {
        // Reset the singleton instance
        personDAO = PersonDAOImplementation.getInstance();
        personDAO.clear(); // Ensures a clean state before each test

        // Create test data
        AppUser user1 = new AppUser("user1", "password123", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("user2", "password456", AppRole.ROLE_APP_USER);

        testPerson1 = new Person(1, "John", "Doe", "john@example.com", user1);
        testPerson2 = new Person(2, "Jane", "Smith", "jane@example.com", user2);
    }

    @Test
    void testPersist_NewPerson_ShouldSucceed() {
        Optional<Person> result = personDAO.persist(testPerson1);
        assertTrue(result.isPresent(), "Persisting a new person should return a non-empty Optional.");
        assertEquals(testPerson1, result.get(), "Persisted person should match the provided person.");
    }

    @Test
    void testPersist_DuplicateId_ShouldFail() {
        personDAO.persist(testPerson1);
        Optional<Person> duplicateResult = personDAO.persist(testPerson1);

        assertTrue(duplicateResult.isEmpty(), "Persisting a person with an existing ID should return an empty Optional.");
    }

    @Test
    void testFindById_PersonExists_ShouldReturnPerson() {
        personDAO.persist(testPerson1);
        Optional<Person> result = personDAO.findById(1);

        assertTrue(result.isPresent(), "Finding a person by ID should return a non-empty Optional if they exist.");
        assertEquals(testPerson1, result.get(), "Returned person should match the stored one.");
    }

    @Test
    void testFindById_PersonDoesNotExist_ShouldReturnEmpty() {
        Optional<Person> result = personDAO.findById(99);
        assertTrue(result.isEmpty(), "Finding a non-existent person should return an empty Optional.");
    }

    @Test
    void testFindByEmail_PersonExists_ShouldReturnPerson() {
        personDAO.persist(testPerson1);
        Optional<Person> result = personDAO.findByEmail("john@example.com");

        assertTrue(result.isPresent(), "Finding a person by email should return a non-empty Optional if they exist.");
        assertEquals(testPerson1, result.get(), "Returned person should match the stored one.");
    }

    @Test
    void testFindByEmail_PersonDoesNotExist_ShouldReturnEmpty() {
        Optional<Person> result = personDAO.findByEmail("nonexistent@example.com");
        assertTrue(result.isEmpty(), "Finding a non-existent person by email should return an empty Optional.");
    }

    @Test
    void testFindAll_ShouldReturnAllPersons() {
        personDAO.persist(testPerson1);
        personDAO.persist(testPerson2);

        Collection<Person> allPersons = personDAO.findAll();

        assertEquals(2, allPersons.size(), "findAll should return all stored persons.");
        assertTrue(allPersons.contains(testPerson1), "findAll should contain testPerson1.");
        assertTrue(allPersons.contains(testPerson2), "findAll should contain testPerson2.");
    }

    @Test
    void testRemove_PersonExists_ShouldBeRemoved() {
        personDAO.persist(testPerson1);
        personDAO.remove(1);
        Optional<Person> result = personDAO.findById(1);

        assertTrue(result.isEmpty(), "Removed person should not be found in the DAO.");
    }

    @Test
    void testRemove_PersonDoesNotExist_ShouldDoNothing() {
        assertDoesNotThrow(() -> personDAO.remove(99), "Removing a non-existent person should not throw an exception.");
    }

    @Test
    void testClear_ShouldRemoveAllPersons() {
        personDAO.persist(testPerson1);
        personDAO.persist(testPerson2);

        personDAO.clear();
        Collection<Person> allPersons = personDAO.findAll();

        assertTrue(allPersons.isEmpty(), "After clearing, findAll should return an empty collection.");
    }
}
