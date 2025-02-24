package data_access_objects;

import model.AppRole;
import model.AppUser;
import model.Person;
import model.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemDAOImplementationTest {
    private TodoItemDAOImplementation dao;
    private TodoItem item1, item2, item3;
    private Person person;

    @BeforeEach
    void setUp() {
        dao = TodoItemDAOImplementation.getInstance();
        dao.clear(); // Ensure a clean state before each test

        person = new Person(1, "John", "Doe", "john.doe@example.com", new AppUser("johndoe", "password", AppRole.ROLE_APP_USER));

        item1 = new TodoItem(1, "Task 1", "Description 1", LocalDate.now().plusDays(1), person);
        item2 = new TodoItem(2, "Task 2", "Description 2", LocalDate.now().minusDays(1), person);
        item3 = new TodoItem(3, "Another Task", "Description 3", LocalDate.now().plusDays(3), person);
    }

    @Test
    void testPersistNewItem() {
        Optional<TodoItem> persisted = dao.persist(item1);
        assertTrue(persisted.isPresent());
        assertEquals(item1, persisted.get());
    }

    @Test
    void testPersistDuplicateItem() {
        dao.persist(item1);
        Optional<TodoItem> duplicate = dao.persist(item1);
        assertFalse(duplicate.isPresent());
    }

    @Test
    void testFindById() {
        dao.persist(item1);
        Optional<TodoItem> found = dao.findById(1);
        assertTrue(found.isPresent());
        assertEquals(item1, found.get());
    }

    @Test
    void testFindAll() {
        dao.persist(item1);
        dao.persist(item2);
        Collection<TodoItem> allItems = dao.findAll();
        assertEquals(2, allItems.size());
        assertTrue(allItems.contains(item1));
        assertTrue(allItems.contains(item2));
    }

    @Test
    void testFindAllByDoneStatus() {
        dao.persist(item1);
        dao.persist(item2);
        dao.persist(item3);

        item1.setDone(true);
        item2.setDone(false);
        item3.setDone(true);

        Collection<TodoItem> doneItems = dao.findAllByDoneStatus(true);
        assertEquals(2, doneItems.size());
        assertTrue(doneItems.contains(item1));
        assertTrue(doneItems.contains(item3));
    }

    @Test
    void testFindByTitleContains() {
        dao.persist(item1);
        dao.persist(item3);
        Collection<TodoItem> results = dao.findByTitleContains("Task");
        assertEquals(2, results.size());
        assertTrue(results.contains(item1));
        assertTrue(results.contains(item3));
    }

    @Test
    void testFindAllByPersonId() {
        dao.persist(item1);
        dao.persist(item2);
        Collection<TodoItem> results = dao.findAllByPersonId(person.getId());
        assertEquals(2, results.size());
    }

    @Test
    void testFindByDeadlineBefore() {
        dao.persist(item1);
        dao.persist(item2);
        Collection<TodoItem> results = dao.findByDeadlineBefore(LocalDate.now());
        assertEquals(1, results.size());
        assertTrue(results.contains(item2));
    }

    @Test
    void testFindByDeadlineAfter() {
        dao.persist(item1);
        dao.persist(item2);
        dao.persist(item3);
        Collection<TodoItem> results = dao.findByDeadlineAfter(LocalDate.now());
        assertEquals(2, results.size());
        assertTrue(results.contains(item1));
        assertTrue(results.contains(item3));
    }

    @Test
    void testRemove() {
        dao.persist(item1);
        dao.remove(item1.getId());
        assertFalse(dao.findById(item1.getId()).isPresent());
    }

    @Test
    void testClear() {
        dao.persist(item1);
        dao.persist(item2);
        dao.clear();
        assertEquals(0, dao.findAll().size());
    }
}
