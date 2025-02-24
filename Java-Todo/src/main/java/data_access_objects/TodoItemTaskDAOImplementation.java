package data_access_objects;

import model.TodoItemTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoItemTaskDAOImplementation implements TodoItemTaskDAO {
    private static final TodoItemTaskDAOImplementation INSTANCE = new TodoItemTaskDAOImplementation();
    private final HashMap<Integer, TodoItemTask> collection = new HashMap<>();

    private TodoItemTaskDAOImplementation() {
    }

    public static TodoItemTaskDAOImplementation getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<TodoItemTask> persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) return Optional.empty();
        collection.put(todoItemTask.getId(), todoItemTask);
        return Optional.of(todoItemTask);
    }

    @Override
    public Optional<TodoItemTask> findById(int id) {
        return Optional.ofNullable(collection.get(id));
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return new ArrayList<>(collection.values());
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        return collection.values().stream()
                .filter(task -> task.isAssigned() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        return collection.values().stream()
                .filter(task -> task.getAssignee() != null && task.getAssignee().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        collection.remove(id);
    }

    @Override
    public void clear() {
        collection.clear();
    }
}
