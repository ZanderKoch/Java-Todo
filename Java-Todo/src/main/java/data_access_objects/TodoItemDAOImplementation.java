package data_access_objects;

import model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoItemDAOImplementation implements TodoItemDAO {
    private static final TodoItemDAOImplementation INSTANCE = new TodoItemDAOImplementation();
    private final HashMap<Integer, TodoItem> collection = new HashMap<>();

    private TodoItemDAOImplementation(){}

    public static TodoItemDAOImplementation getInstance(){
        return INSTANCE;
    }

    @Override
    public Optional<TodoItem> persist(TodoItem todoItem) {
        if (collection.containsKey(todoItem.getId())){
            return Optional.empty();
        }
        else {
            collection.put(todoItem.getId(),todoItem);
            return Optional.of(todoItem);
        }
    }

    @Override
    public Optional<TodoItem> findById(int id) {
        return Optional.ofNullable(collection.get(id));
    }

    @Override
    public Collection<TodoItem> findAll() {
        return collection.values();
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return collection.values().stream().filter(
                todoItem -> todoItem.isDone() == done
        ).collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return collection.values().stream().filter(
                todoItem -> todoItem.getTitle().toLowerCase().contains(title.toLowerCase())
        ).collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findAllByPersonId(int PersonId) {
        return collection.values().stream().filter(
                todoItem -> todoItem.getCreator().getId() == PersonId
        ).collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        return collection.values().stream().filter(
                todoItem -> todoItem.getDeadline().isBefore(date)
        ).collect(Collectors.toList());
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        return collection.values().stream().filter(
                todoItem -> todoItem.getDeadline().isAfter(date)
        ).collect(Collectors.toList());
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
