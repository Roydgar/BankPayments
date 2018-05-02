package ua.training.model.entity;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAdmin() {return role == Role.ADMIN; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public enum Role {
        USER, ADMIN, UNKNOWN
    }

    public static class UserBuilder {
        private User user = new User();

        public UserBuilder() {
        }

        public UserBuilder setId(int id) {
            user.setId(id);
            return this;
        }

        public UserBuilder setUsername(String name) {
            user.setUsername(name);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public User create() { return user; }
    }
}
