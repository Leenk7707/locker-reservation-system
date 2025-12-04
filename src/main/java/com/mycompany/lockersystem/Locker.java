package com.mycompany.lockersystem;

public class Locker {
    private int id;
    private boolean isReserved;
    private String reservedBy; // only ID 
    
    private String college;
   
    
    public Locker(int id, String college) {
        this.id = id;
        this.college = college;
        this.isReserved = false;
        this.reservedBy = null;
    }

    Locker(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public void reserve(String user) {
        this.isReserved = true;
        this.reservedBy = user;
    }
    
    @Override
public String toString() {
    return "Locker " + id + " | College: " + college + " | Reserved: " + isReserved;
}

    
}
