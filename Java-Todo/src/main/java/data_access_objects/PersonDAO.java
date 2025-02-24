package data_access_objects;

import model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonDAO {
    Optional<Person> persist(Person person);

    Optional<Person> findById(int id);

    Optional<Person> findByEmail(String email);

    Collection<Person> findAll();

    void remove(int id);

    void clear();
}
