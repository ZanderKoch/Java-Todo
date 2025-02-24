package data_access_objects;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTaskDAOImplementationTest {
    private TodoItemTaskDAOImplementation dao;
    private TodoItemTask task1;
    private TodoItemTask task2;
    private Person assignee;
    private AppUser appUser;
    private TodoItem todoItem1;
    private TodoItem todoItem2;

    @BeforeEach
    void setUp() {
        dao = TodoItemTaskDAOImplementation.getInstance();
        dao.clear();

        appUser = new AppUser("johndoe", "password123", AppRole.ROLE_APP_USER);
        assignee = new Person(1, "John", "Doe", "john.doe@example.com", appUser);

        todoItem1 = new TodoItem(201, "Task 1", "First task description", LocalDate.of(2025, 2, 28), assignee);
        todoItem2 = new TodoItem(202, "Task 2", "Second task description", LocalDate.of(2025, 3, 15), assignee);

        task1 = new TodoItemTask(101, todoItem1, assignee);
        task2 = new TodoItemTask(102, todoItem2);
    }


    @Test
    void testPersist() {
        Optional<TodoItemTask> persistedTask = dao.persist(task1);
        assertTrue(persistedTask.isPresent());
        assertEquals(task1, persistedTask.get());
    }

    @Test
    void testFindById() {
        dao.persist(task1);
        Optional<TodoItemTask> foundTask = dao.findById(101);
        assertTrue(foundTask.isPresent());
        assertEquals(task1, foundTask.get());
    }

    @Test
    void testFindAll() {
        dao.persist(task1);
        dao.persist(task2);
        Collection<TodoItemTask> allTasks = dao.findAll();
        assertEquals(2, allTasks.size());
    }

    @Test
    void testFindByAssignedStatus() {
        dao.persist(task1);
        dao.persist(task2);
        Collection<TodoItemTask> assignedTasks = dao.findByAssignedStatus(true);
        assertEquals(1, assignedTasks.size());
        assertTrue(assignedTasks.contains(task1));
    }

    @Test
    void testFindByPersonId() {
        dao.persist(task1);
        dao.persist(task2);
        Collection<TodoItemTask> personTasks = dao.findByPersonId(1);
        assertEquals(1, personTasks.size());
        assertTrue(personTasks.contains(task1));
    }

    @Test
    void testRemove() {
        dao.persist(task1);
        dao.remove(101);
        assertFalse(dao.findById(101).isPresent());
    }

    @Test
    void testClear() {
        dao.persist(task1);
        dao.persist(task2);
        dao.clear();
        assertTrue(dao.findAll().isEmpty());
    }
}
