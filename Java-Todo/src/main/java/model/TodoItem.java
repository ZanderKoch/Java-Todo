package model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {

    private final int id;
    private String title;
    private String taskDescription;
    private LocalDate deadline;
    private Person creator;
    private boolean done;

    public TodoItem(
            int id,
            String title,
            String taskDescription,
            LocalDate deadline,
            Person creator) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title may not be null or empty");
        }
        if (deadline == null) {
            throw new IllegalArgumentException("deadline may not be null");
        }

        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.creator = creator;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("title may not be null or empty");
        }
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null) {
            throw new IllegalArgumentException("deadline may not be null");
        }
        this.deadline = deadline;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, title: %s, taskDescription: %s, deadline: %s, done: %b}",
                id,
                title,
                taskDescription == null ? "No description" : taskDescription,
                deadline,
                done);
    }

    public boolean isOverdue() {
        return deadline.isBefore(LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(taskDescription, todoItem.taskDescription) && Objects.equals(deadline, todoItem.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, deadline, done);
    }
}
