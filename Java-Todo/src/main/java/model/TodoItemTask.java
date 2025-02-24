package model;

import sequencers.TodoItemTaskIdSequencer;

import java.util.Objects;

public class TodoItemTask {
    private final int id;
    private boolean assigned; /*I have no idea why this is its own field
        and I would not have done it this way if the instructions didn't
        force me to, because it introduces side effects and an edge case*/
    private TodoItem todoItem;
    private Person assignee;

    /**
     * Creates a new TodoItemTask with a unique ID assigned by the sequencer.
     * This constructor is intended for production use.
     *
     * @param todoItem the todo item associated with this task (must not be null)
     */
    public TodoItemTask(TodoItem todoItem) {
        this.id = TodoItemTaskIdSequencer.getInstance().nextId();
        this.setTodoItem(todoItem);
    }

    /**
     * Creates a new TodoItemTask with a unique ID assigned by the sequencer and assigns it to a person.
     * This constructor is intended for production use.
     *
     * @param todoItem the todo item associated with this task (must not be null)
     * @param assignee the person assigned to this task (can be null)
     */
    public TodoItemTask(TodoItem todoItem, Person assignee) {
        this.id = TodoItemTaskIdSequencer.getInstance().nextId();
        this.setTodoItem(todoItem);
        this.setAssignee(assignee);
    }

    /**
     * Creates a new TodoItemTask with a manually specified ID.
     * This constructor is intended for testing purposes only.
     *
     * @param id       the unique ID for this task
     * @param todoItem the todo item associated with this task (must not be null)
     */
    public TodoItemTask(int id, TodoItem todoItem) {
        this.id = id;
        this.setTodoItem(todoItem);
    }

    /**
     * Creates a new TodoItemTask with a manually specified ID.
     * This constructor is intended for testing purposes only.
     *
     * @param id       the unique ID for this task
     * @param todoItem the todo item associated with this task (must not be null)
     */
    public TodoItemTask(int id, TodoItem todoItem, Person assignee) {
        this.id = id;
        this.setTodoItem(todoItem);
        this.setAssignee(assignee);

    }

    public int getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        if (assigned & assignee == null) {
            throw new IllegalStateException("Cannot set assigned to true when no assignee is provided");
        } else {
            this.assigned = assigned;
            this.assignee = null;
        }

    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("todoItem may not be null");
        }
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        if (assignee == null) {
            this.assignee = null;
            this.assigned = false;
        } else {
            this.assignee = assignee;
            this.assigned = true;
        }
    }

    @Override
    public String toString() {
        return String.format("{id: %d, assigned: %b, todoItem: %s}",
                this.id,
                this.assigned,
                this.todoItem.getTitle()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todoItem, that.todoItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todoItem);
    }
}


