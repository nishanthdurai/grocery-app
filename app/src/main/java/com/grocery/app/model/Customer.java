package com.grocery.app.model;

public class Customer {
    private String email;
    private String password;
    private String id;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getId() {
        return id;
    }

    public Customer() {
    }

    public Customer(String email, String password, String id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }
}
