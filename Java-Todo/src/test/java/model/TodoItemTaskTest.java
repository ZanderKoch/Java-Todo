package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {

    private TodoItemTask todoItemTask;
    private TodoItem todoItem;
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person(4, "Nisse", "Olsson", "nisse@gmail.com");
        todoItem = new TodoItem(1, "Add unit tests", "All Classes should have a test class", LocalDate.of(1993, 12, 10), person);
        todoItemTask = new TodoItemTask(1, todoItem);
    }

    @Test
    void summary() {
        assertEquals("{id: 1, assigned: false, todoItem: Add unit tests, assignee: No assignee}",
                todoItemTask.getSummary());
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
}
