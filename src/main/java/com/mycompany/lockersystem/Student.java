/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lockersystem;

/**
 *
 * @author danab
 */
public class Student extends User {
    private String college;
    private boolean hasReservation;
    private boolean specialNeeds;

    public Student(String id, String password, String college) {
        super(id, password, "STUDENT");
        this.college = college;
        this.hasReservation = false;
        this.specialNeeds = false;
    }

    public String getCollege() { return college; }
    public boolean hasReservation() { return hasReservation; }
    public void setHasReservation(boolean status) { this.hasReservation = status; }
    

public void setSpecialNeeds(boolean val) {
    this.specialNeeds = val;
}

public boolean isSpecialNeeds() {
    return specialNeeds;
}

    
}



