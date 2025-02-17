package data_access_objects;

import model.AppUser;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class AppUserDAOImplementation implements AppUserDAO {
    private static final AppUserDAOImplementation INSTANCE = new AppUserDAOImplementation();
    private final HashMap<String, AppUser> collection = new HashMap<>();

    private AppUserDAOImplementation() {
    }

    public static AppUserDAOImplementation getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<AppUser> persist(AppUser appUser) {
        if (collection.containsKey(appUser.getUsername())){
            return Optional.empty();
        } else {
            collection.put(appUser.getUsername(),appUser);
            return Optional.of(appUser);
        }
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return Optional.ofNullable(collection.get(username));
    }

    @Override
    public Collection<AppUser> findAll() {
        return collection.values();
    }

    @Override
    public void remove(String username) {
        collection.remove(username);
    }

    @Override
    public void clear() {
        collection.clear();
    }
}
