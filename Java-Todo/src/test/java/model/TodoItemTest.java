package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {

    private TodoItem todoItem;
    private Person creator;
    private AppUser user;


    @BeforeEach
    void setUp() {
        user = new AppUser("NOlsson", "password123", AppRole.ROLE_APP_USER);
        creator = new Person("Nisse", "Olsson", "nisse@gmail.com", user);
        todoItem = new TodoItem(1, "Test Task", "Description", LocalDate.now().plusDays(1), creator);
    }

    @Test
    void testToString() {
        String expected = String.format(
                "{id: 1, title: Test Task, taskDescription: Description, deadline: %s, done: false}",
                LocalDate.now().plusDays(1)
        );
        assertEquals(expected, todoItem.toString());
    }

    @Test
    void isOverdueReturnsFalseForFutureDeadline() {
        assertFalse(todoItem.isOverdue(), "Expected isOverdue to return false for a future deadline.");
    }

    @Test
    void isOverdueReturnsTrueForPastDeadline() {
        todoItem.setDeadline(LocalDate.now().minusDays(1));
        assertTrue(todoItem.isOverdue(), "Expected isOverdue to return true for a past deadline.");
    }

    @Test
    void throwsExceptionWhenTitleIsEmptyInConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new TodoItem(1, "", "Description", LocalDate.now(), creator),
                "Expected IllegalArgumentException when title is empty."
        );
    }

    @Test
    void throwsExceptionWhenDeadlineIsNullInConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new TodoItem(1, "Test Task", "Description", null, creator),
                "Expected IllegalArgumentException when deadline is null."
        );
    }

    @Test
    void throwsExceptionWhenSettingTitleToEmpty() {
        TodoItem todoItem = new TodoItem(1, "Test Task", "Description", LocalDate.now(), creator);
        assertThrows(IllegalArgumentException.class,
                () -> todoItem.setTitle(""),
                "Expected IllegalArgumentException when setting title to empty."
        );
    }

    @Test
    void throwsExceptionWhenSettingDeadlineToNull() {
        TodoItem todoItem = new TodoItem(1, "Test Task", "Description", LocalDate.now(), creator);
        assertThrows(IllegalArgumentException.class,
                () -> todoItem.setDeadline(null),
                "Expected IllegalArgumentException when setting deadline to null."
        );
    }

    @Test
    void allowsSettingValidTitle() {
        TodoItem todoItem = new TodoItem(1, "Test Task", "Description", LocalDate.now(), creator);
        todoItem.setTitle("New Title");
        assertEquals("New Title", todoItem.getTitle());
    }

    @Test
    void allowsSettingValidDeadline() {
        TodoItem todoItem = new TodoItem(1, "Test Task", "Description", LocalDate.now(), creator);
        LocalDate newDeadline = LocalDate.now().plusDays(5);
        todoItem.setDeadline(newDeadline);
        assertEquals(newDeadline, todoItem.getDeadline());
    }

    @Test
    void testEquals_SameProperties() {
        TodoItem otherTodo = new TodoItem(1, "Test Task", "Description", LocalDate.now().plusDays(1), creator);
        assertEquals(todoItem, otherTodo, "TodoItems with the same properties should be equal.");
    }

    @Test
    void testNotEquals_DifferentId() {
        TodoItem otherTodo = new TodoItem(2, "Test Task", "Description", LocalDate.now().plusDays(1), creator);
        assertNotEquals(todoItem, otherTodo, "TodoItems with different IDs should not be equal.");
    }

    @Test
    void testEquals_HashCodeContract() {
        TodoItem otherTodo = new TodoItem(1, "Test Task", "Description", LocalDate.now().plusDays(1), creator);
        assertEquals(todoItem.hashCode(), otherTodo.hashCode(), "Equal objects must have the same hash code.");
    }

    @Test
    void testHashCode_NotEquals() {
        TodoItem otherTodo = new TodoItem(2, "Test Task", "Description", LocalDate.now().plusDays(1), creator);
        assertNotEquals(todoItem.hashCode(), otherTodo.hashCode(), "Different objects should ideally have different hash codes.");
    }
}

