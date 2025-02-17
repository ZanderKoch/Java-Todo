package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

    private AppUser defaultUser;

    @BeforeEach
    public void setUp() {
        defaultUser = new AppUser("john", "password123", AppRole.ROLE_APP_USER);
    }

    // --- Constructor Tests ---

    @Test
    public void testConstructorValid() {
        AppUser user = new AppUser("john", "password123", AppRole.ROLE_APP_USER);
        assertEquals("john", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(AppRole.ROLE_APP_USER, user.getRole());
    }

    @Test
    public void testConstructorNullUsername() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser(null, "password123", AppRole.ROLE_APP_USER);
        });
        assertEquals("username may not be null", thrown.getMessage());
    }

    @Test
    public void testConstructorNullPassword() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("john", null, AppRole.ROLE_APP_USER);
        });
        assertEquals("password may not be null", thrown.getMessage());
    }

    @Test
    public void testConstructorNullRole() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new AppUser("john", "password123", null);
        });
        assertEquals("role may not be null", thrown.getMessage());
    }

    // --- Setter Tests ---

    @Test
    public void testSetUsernameValid() {
        defaultUser.setUsername("jane");
        assertEquals("jane", defaultUser.getUsername());
    }

    @Test
    public void testSetUsernameNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            defaultUser.setUsername(null);
        });
        assertEquals("username may not be null", thrown.getMessage());
    }

    @Test
    public void testSetPasswordValid() {
        defaultUser.setPassword("newPassword");
        assertEquals("newPassword", defaultUser.getPassword());
    }

    @Test
    public void testSetPasswordNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            defaultUser.setPassword(null);
        });
        assertEquals("password may not be null", thrown.getMessage());
    }

    @Test
    public void testSetRoleValid() {
        defaultUser.setRole(AppRole.ROLE_APP_USER);
        assertEquals(AppRole.ROLE_APP_USER, defaultUser.getRole());
    }

    @Test
    public void testSetRoleNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            defaultUser.setRole(null);
        });
        assertEquals("role may not be null", thrown.getMessage());
    }

    // --- equals() Tests ---

    @Test
    public void testEqualsSameObject() {
        assertEquals(defaultUser, defaultUser);
    }


    @Test
    public void testEqualsDifferentPassword() {
        // Create another AppUser with the same username and role but a different password.
        AppUser otherUser = new AppUser("john", "differentPassword", AppRole.ROLE_APP_USER);
        assertEquals(defaultUser, otherUser);
    }

    @Test
    public void testEqualsDifferentUsername() {
        AppUser otherUser = new AppUser("jane", "password123", AppRole.ROLE_APP_USER);
        assertNotEquals(defaultUser, otherUser);
    }

    @Test
    public void testEqualsDifferentRole() {
        AppUser otherUser = new AppUser("john", "password123", AppRole.ROLE_APP_ADMIN);
        assertNotEquals(defaultUser, otherUser);
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(null, defaultUser);
    }

    @Test
    public void testEqualsDifferentClass() {
        String notAUser = "Not a user";
        assertNotEquals(defaultUser, notAUser);
    }

    // --- hashCode() Tests ---

    @Test
    public void testHashCodeEqualForEqualObjects() {
        AppUser otherUser = new AppUser("john", "differentPassword", AppRole.ROLE_APP_USER);
        assertEquals(defaultUser.hashCode(), otherUser.hashCode());
    }

    // --- toString() Tests ---

    @Test
    public void testToString() {
        String result = defaultUser.toString();
        assertTrue(result.contains("john"));
        assertTrue(result.contains("USER"));
    }
}