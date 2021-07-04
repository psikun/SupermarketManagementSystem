package com.management.domain;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author SiKun
 * @date 2021/05/20/ 13:05
 */

public class User {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleBooleanProperty isAdmin = new SimpleBooleanProperty();
    private SimpleBooleanProperty deleted = new SimpleBooleanProperty();

    public User() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isIsAdmin() {
        return isAdmin.get();
    }

    public SimpleBooleanProperty isAdminProperty() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin.set(isAdmin);
    }

    public boolean isDeleted() {
        return deleted.get();
    }

    public SimpleBooleanProperty deletedProperty() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted.set(deleted);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", isAdmin=" + isAdmin +
                ", deleted=" + deleted +
                '}';
    }
}
