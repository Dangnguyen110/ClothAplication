package com.example.clothaplication;

public class ManagerAccount {
    private int id;           // Unique identifier for the manager
    private String email;     // Email address of the manager
    private String password;  // Password for the manager

    // Constructor
    public ManagerAccount(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Authenticate the manager using email and password
    public boolean authenticate(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "ManagerAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}

