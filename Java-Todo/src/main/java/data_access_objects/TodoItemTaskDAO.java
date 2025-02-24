package data_access_objects;

import model.TodoItemTask;

import java.util.Collection;
import java.util.Optional;

public interface TodoItemTaskDAO {
    Optional<TodoItemTask> persist(TodoItemTask todoItemTask);

    Optional<TodoItemTask> findById(int id);

    Collection<TodoItemTask> findAll();

    Collection<TodoItemTask> findByAssignedStatus(boolean status);

    Collection<TodoItemTask> findByPersonId(int personId);

    void remove(int id);

    void clear();
}
