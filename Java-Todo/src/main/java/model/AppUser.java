package model;

import java.util.Objects;

public class AppUser {
    private String username;
    private String password;
    private AppRole role;

    public AppUser(String username, String password, AppRole role) {
        if (username == null) {
            throw new IllegalArgumentException("username may not be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password may not be null");
        }
        if (role == null) {
            throw new IllegalArgumentException("role may not be null");
        }
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username may not be null");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password may not be null");
        }
        this.password = password;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        if (role == null) {
            throw new IllegalArgumentException("role may not be null");
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("{username: %s role: %s", this.username, this.role);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}