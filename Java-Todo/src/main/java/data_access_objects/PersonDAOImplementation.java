package data_access_objects;

import model.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class PersonDAOImplementation implements PersonDAO {
    private static final PersonDAOImplementation INSTANCE = new PersonDAOImplementation();
    private final HashMap<Integer, Person> collection = new HashMap<>();

    private PersonDAOImplementation() {
    }

    public static PersonDAOImplementation getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Person> persist(Person person) {
        if (collection.containsKey(person.getId())) {
            return Optional.empty();
        } else {
            collection.put(person.getId(), person);
            return Optional.of(person);
        }
    }

    @Override
    public Optional<Person> findById(int id) {
        return Optional.ofNullable(collection.get(id));
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return collection.values().stream()
                .filter(
                        person -> person.getEmail().equals(email)
                ).findFirst();
    }

    @Override
    public Collection<Person> findAll() {
        return collection.values();
    }

    @Override
    public void remove(int id) {
        collection.remove(id);
    }

    public void clear() {
        collection.clear();
    }
}