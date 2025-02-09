package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {

    private TodoItemTask todoItemTask;
    private TodoItem todoItem;
    private Person person;
    private AppUser user;

    @BeforeEach
    void setUp() {
        AppUser user = new AppUser("NOlsson", "password123", AppRole.ROLE_APP_USER);
        person = new Person(4, "Nisse", "Olsson", "nisse@gmail.com", user);
        todoItem = new TodoItem(1, "Add unit tests", "All Classes should have a test class", LocalDate.of(1993, 12, 10), person);
        todoItemTask = new TodoItemTask(1, todoItem);
    }

    @Test
    void testToString() {
        assertEquals("{id: 1, assigned: false, todoItem: Add unit tests}",
                todoItemTask.toString());
    }

    @Test
    void setAssigneeToAPerson() {
        todoItemTask.setAssignee(person);
        assertEquals(todoItemTask.getAssignee(), person);
        assertTrue(todoItemTask.isAssigned());
    }

    @Test
    void unsetAssignee() {
        // set an assignee first, otherwise it wouldn't prove whether it worked
        todoItemTask.setAssignee(person);
        todoItemTask.setAssignee(null);
        assertNull(todoItemTask.getAssignee());
        assertFalse(todoItemTask.isAssigned());
    }

    @Test
    void setAssignedFalseWithoutAssignee() {
        todoItemTask.setAssigned(false);
        assertFalse(todoItemTask.isAssigned());
        assertNull(todoItemTask.getAssignee());
    }

    @Test
    void unsetAssignedFalseWithAssignee() {
        //set an assignee first
        todoItemTask.setAssignee(person);
        todoItemTask.setAssigned(false);
        assertFalse(todoItemTask.isAssigned());
        assertNull(todoItemTask.getAssignee());
    }

    @Test
    void setAssignedTrueWithoutAssignee() {

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                        todoItemTask.setAssigned(true),
                "Setting assigned to true without an assignee should throw an exception"
        );
    }

    @Test
    void setAssignedTrueWithAssignee() {
        //set an assignee first
        todoItemTask.setAssignee(person);
        todoItemTask.setAssigned(true);
    }

    @Test
    void testEquals_SameProperties() {
        TodoItemTask otherTask = new TodoItemTask(1, todoItem);
        assertEquals(todoItemTask, otherTask, "TodoItemTasks with the same properties should be equal.");
    }

    @Test
    void testNotEquals_DifferentId() {
        TodoItemTask otherTask = new TodoItemTask(2, todoItem);
        assertNotEquals(todoItemTask, otherTask, "TodoItemTasks with different IDs should not be equal.");
    }

    @Test
    void testEquals_HashCodeContract() {
        TodoItemTask otherTask = new TodoItemTask(1, todoItem);
        assertEquals(todoItemTask.hashCode(), otherTask.hashCode(), "Equal objects must have the same hash code.");
    }

    @Test
    void testHashCode_NotEquals() {
        TodoItemTask otherTask = new TodoItemTask(2, todoItem);
        assertNotEquals(todoItemTask.hashCode(), otherTask.hashCode(), "Different objects should ideally have different hash codes.");
    }
}
