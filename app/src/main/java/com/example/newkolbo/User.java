package com.example.newkolbo;

public class User {
    private String phone;
    private String username;
    private String email;
    private String address;
    private String password;

    public User() {
    }

    public User(String phone, String username, String address, String email, String password) {
        this.phone = phone;
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
