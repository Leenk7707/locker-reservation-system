package com.mycompany.lockersystem;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class LockerSystem {
    
    private List<Locker> lockers = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    
    public LockerSystem(int totalLockers) {
        this.lockers = new ArrayList<>();
        this.users = new ArrayList<>();

    
        int lockersPerCollege = totalLockers / 3; // divid total number of Lockers into 3 colleges for example 

        for (int i = 1; i <= totalLockers; i++) {

            if (i <= lockersPerCollege) {
                lockers.add(new Locker(i, "Engineering"));
            }
            else if (i <= lockersPerCollege * 2) {
                lockers.add(new Locker(i, "IT"));
            }
            else {
                lockers.add(new Locker(i, "Medicine"));
            }
        }
    }



    public boolean reserveLocker(int lockerId, Student student) {
        
        
        
        // if the student is a special case prioritize their reservation 
        if(student.isSpecialNeeds()){
            
            for(Locker locker : lockers){
                
                if(locker.getId() == lockerId && !locker.isReserved()&& locker.getCollege().equals(student.getCollege())){
                    
                    locker.reserve(student.getId());
                    student.setHasReservation(true);
                    writeOutput("Special Needs Student " + student.getId() + " reserved Locker " + lockerId);
                    return true;   
                }  
            }
            
            return false;  
        }
        
        // Check if ANY special needs student still has priority
        for(User u : users){
            
            if (u instanceof Student ss && ss.isSpecialNeeds() && !ss.hasReservation()) {
                
                 System.out.println("Priority reserved for special needs student. You cannot reserve now.");
                 return false;
             }
            
        }
        
        // for normal students 
        for (Locker locker : lockers) {
            
            if (locker.getId() == lockerId && !locker.isReserved() && locker.getCollege().equals(student.getCollege())) {
                
                locker.reserve(student.getId());
                student.setHasReservation(true);
                writeOutput("Student " + student.getId() + " reserved Locker " + lockerId);

                return true;
            }
        }
    return false;
    }
    


    public List<Locker> viewAvailableLockers(Student student) {
        
        List<Locker> available = new ArrayList<>();
        
        for (Locker locker : lockers) {
            
            if (!locker.isReserved() && locker.getCollege().equals(student.getCollege())) {
                available.add(locker);
            }
        }
        return available;
    }
    
    
    
    public void displayLockers() {
        System.out.println("\nLocker Status:");
        for (Locker locker : lockers) {
            System.out.println("Locker " + locker.getId() + ": " + 
                (locker.isReserved() ? "Reserved by " + locker.getReservedBy() : "Available"));
        }
    }
    
    public void addLocker(int id, String college) {
        for (Locker l : lockers) {
            if (l.getId() == id) {
                System.out.println("Error: Locker ID already exists!");
                return;
            }
        }
        lockers.add(new Locker(id, college));
        System.out.println("Success: Locker " + id + " added.");
        saveData();
    }

    public void removeLocker(int id) {
        Locker toRemove = null;
        for (Locker l : lockers) {
            if (l.getId() == id) {
                toRemove = l;
                break;
            }
        }
        
        if (toRemove != null) {
            if (toRemove.isReserved()) {
                System.out.println("Error: Cannot delete a reserved locker.");
            } else {
                lockers.remove(toRemove);
                System.out.println("Success: Locker " + id + " deleted.");
                saveData();
            }
        } else {
            System.out.println("Error: Locker not found.");
        }
    }
    
    
    public List<Locker> getAllLockers() {
    return lockers;
}

    public void addUser(User user) {
    users.add(user);
}

    public User login(String id, String password) {
        for (User u : users) {
             if (u.getId().equals(id) && u.login(password)) {
                return u;
             }
        }
        return null;
}
  
private void saveData() {
    // not implemented
}

public User getUserById(String id) {
    for (User user : users) {
        if (user.getId().equals(id)) {
            return user;
        }
    }
    return null;
}


public void loadUsersFromFile(String filename) {
    try (Scanner scan = new Scanner(new File(filename))) {

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");

            // Student: S,ID,PASSWORD,COLLEGE,SPECIAL_NEEDS
            if (parts[0].equals("S")) {
                String id = parts[1];
                String pass = parts[2];
                String college = parts[3];
                boolean special = parts[4].equalsIgnoreCase("Y");

                Student s = new Student(id, pass, college);
                s.setSpecialNeeds(special);
                addUser(s);
            }

            // Staff: T,ID,PASSWORD
            else if (parts[0].equals("T")) {
                String id = parts[1];
                String pass = parts[2];

                Staff t = new Staff(id, pass);
                addUser(t);
            }
        }

        System.out.println("Users loaded from file successfully!");

    } catch (Exception e) {
        System.out.println("ERROR loading users file: " + e.getMessage());
    }
}

private void writeOutput(String text) {
    try {
        System.out.println("Writing to: " + new File("output.txt").getAbsolutePath());

        FileWriter fw = new FileWriter("output.txt", true); // append = true
        fw.write(text + "\n");
        fw.close();
    } catch (Exception e) {
        System.out.println("Error writing output file: " + e.getMessage());
    }
}

}

