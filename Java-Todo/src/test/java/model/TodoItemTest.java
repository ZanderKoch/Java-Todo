package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {

    private TodoItem todoItem;
    private Person creator;

    @BeforeEach
    void setUp() {
        creator = new Person(1, "Nisse", "Olsson", "nisse@gmail.com");
        todoItem = new TodoItem(1, "Test Task", "Description", LocalDate.now().plusDays(1), creator);
    }

    @Test
    void getSummaryReturnsCorrectFormat() {
        String expected = String.format(
                "{id: 1, title: 'Test Task', taskDescription: 'Description', deadline: '%s', creator: '{id: 1, name: Nisse Olsson, email: nisse@gmail.com}', done: false}",
                LocalDate.now().plusDays(1)
        );
        assertEquals(expected, todoItem.getSummary());
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
}

