package model;

public class TodoItemTask {
    private int id;
    private boolean assigned; /*I have no idea why this is its own field
        and I would not have done it this way if the instructions didn't
        force me to, because it introduces side effects and an edge case*/
    private TodoItem todoItem;
    private Person assignee;

    public TodoItemTask(int id, TodoItem todoItem) {
        this.id = id;
        this.todoItem = todoItem;
    }

    public TodoItemTask(int id, TodoItem todoItem, Person assignee) {
        this.setAssignee(getAssignee());

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

    public String getSummary() {
        return String.format("{id: %d, assigned: %b, todoItem: %s, assignee: %s}",
                this.id,
                this.assigned,
                this.todoItem.getTitle(),
                this.assignee != null ? this.assignee.getFullName() : "No assignee");
    }
}


