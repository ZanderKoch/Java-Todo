package model;

import java.time.LocalDate;

public class TodoItem {

    private int id;
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
        if (title.isEmpty()){
            throw new IllegalArgumentException("title may not be null or empty");
        }
        if(deadline == null) {
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
        if (title.isEmpty()){
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
        if(deadline == null) {
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

    public String getSummary() {
        return String.format("{id: %d, title: '%s', taskDescription: '%s', deadline: '%s', creator: '%s', done: %b}",
                id,
                title,
                taskDescription == null ? "No description" : taskDescription,
                deadline,
                creator != null ? creator.getSummary() : "No creator",
                done);
    }

    public boolean isOverdue() {
        return deadline.isBefore(LocalDate.now());
    }
}
