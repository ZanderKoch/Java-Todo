package data_access_objects;
import model.AppUser;

import java.util.Collection;
import java.util.Optional;

public interface AppUserDAO {
    Optional<AppUser> persist(AppUser appUser);
    Optional<AppUser> findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(String username);
    void clear();
}