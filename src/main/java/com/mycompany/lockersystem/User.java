/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lockersystem;

/**
 *
 * @author danab
 */
public abstract class User {
    protected String id;
    protected String password;
    protected String role; // "STUDENT" or "STAFF"

    public User(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }
    public String getRole() { return role; }
    
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
