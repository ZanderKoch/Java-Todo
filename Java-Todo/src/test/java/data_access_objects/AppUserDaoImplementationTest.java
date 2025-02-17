package data_access_objects;

import model.AppRole;
import model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserDaoImplementationTest {
    private AppUserDAOImplementation dao;
    private AppUser appUser1;
    private AppUser appUser2;

    @BeforeEach
    public void setUp() {
        dao = AppUserDAOImplementation.getInstance();
        dao.clear();
        appUser1 = new AppUser("test1", "qwerty123", AppRole.ROLE_APP_USER);
        appUser2 = new AppUser("test2", "asdfgh456", AppRole.ROLE_APP_ADMIN);
    }

    @Test
    public void persistAppUser() {
        Optional<AppUser> persisted = dao.persist(appUser1);
        assertTrue(persisted.isPresent());
        assertEquals(appUser1, persisted.get());
    }

    @Test
    public void persistDuplicateAppUser() {
        Optional<AppUser> persisted = dao.persist(appUser1);
        assertTrue(persisted.isPresent());
        persisted = dao.persist(appUser1);
        assertFalse(persisted.isPresent());
    }

    @Test
    public void findPresentByUsername() {
        dao.persist(appUser1);
        dao.persist(appUser2);
        Optional<AppUser> found = dao.findByUsername(appUser2.getUsername());
        assertTrue(found.isPresent());
        assertEquals(appUser2, found.get());
    }

    @Test
    public void findAbsentByUsername() {
        Optional<AppUser> found = dao.findByUsername(appUser2.getUsername());
        assertFalse(found.isPresent());
    }

    @Test
    public void removePresent() {
        dao.persist(appUser2);

        dao.remove(appUser2.getUsername());

        Optional<AppUser> found = dao.findByUsername(appUser2.getUsername());
        assertFalse(found.isPresent());
    }

    @Test
    public void removeAbsent() {
        dao.remove(appUser1.getUsername());
    }

    
}
