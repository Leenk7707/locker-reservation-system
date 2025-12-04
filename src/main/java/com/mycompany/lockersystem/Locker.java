package com.mycompany.lockersystem;

public class Locker {
    private int id;
    private boolean isReserved;
    private String reservedBy; // only ID 
    
    private String college;
    private String location;

    public Locker(int id) {
        this.id = id;
        this.isReserved = false;
        this.reservedBy = null;
        this.college = null;
        this.location = null;
    }
    
    public Locker(int id, String college, String location) {
        this.id = id;
        this.college = college;
        this.location = location;
        this.isReserved = false;
        this.reservedBy = null;
    }

    public int getId() {
        return id;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }
    
    public String getCollege() {
        return college;
    }

    public String getLocation() {
        return location;
    }

    public void reserve(String user) {
        this.isReserved = true;
        this.reservedBy = user;
    }
    
}
