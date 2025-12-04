package com.mycompany.lockersystem;


import com.mycompany.lockersystem.LockerSystem;
import java.util.Scanner;


// instruction to start :
// to run as student : ID: 2208888, Password: abc987
// to run as Staff: ID: 9000001, Password: admin1
//or any ID and Password from Input file 

public class Main {
   

    public static void main(String[] args) {
       
        
        Scanner in = new Scanner(System.in);
        LockerSystem system = new LockerSystem(10); // to create locker system ( 10 lockers 
        
        system.loadUsersFromFile("users.txt"); // here is the input file 
        ReportSystemController reportController = new ReportSystemController(system);// create Report controller to use later 
        
        System.out.println("=== Locker Reservation System ===");
        
        while(true){
            System.out.println("=== Login ===");// login interface 
            
            System.out.print("Enter ID(0 to exit): ");
            String id = in.nextLine();
            if (id.equals("0")) {
                System.out.println("Exiting system...");
                break;
            } 
            System.out.print("Enter Password: ");
            String pw = in.nextLine();
            
            User logged = system.login(id, pw);
            
            if (logged == null ) {
             System.out.println("Invalid username or password! try again ");
             continue;
            }
            
            System.out.println("Welcome, " + logged.getRole());
         
            // opening a list based on the role 
            if (logged.getRole().equals("STUDENT")) {
              studentMenu(in, system, (Student) logged);
             } 
            else {
              staffMenu(in, system, reportController);
            }
        }
    }
    
    private static void studentMenu(Scanner in, LockerSystem system, Student student) {
        while (true) {
            System.out.println("\n1. View Available Lockers (My College: " + student.getCollege() + ")");
            System.out.println("2. Reserve a Locker");
            System.out.println("3. Logout");
            System.out.print("Choose: ");
            int choice = in.nextInt();
            in.nextLine();

            if (choice == 1) {
                var list = system.viewAvailableLockers(student);// to view Available Lockers only *
                

                if (list.isEmpty()) 
                    System.out.println("No lockers available for your college.");
                else 
                    for (Locker l : list) System.out.println(l);
                
            } else if (choice == 2) {
                
                System.out.print("Enter Locker Number: ");
                int lId = in.nextInt();
                in.nextLine();
                boolean success = system.reserveLocker(lId, student); 
                if (success) System.out.println("Reservation Confirmed!");// although it's full in the run it's still reserve *
                
            } else {
                break;
            }
        }
    }
    
    
    private static void staffMenu(Scanner in, LockerSystem system, ReportSystemController report) {
        while (true) {
            System.out.println("\n1. Add Locker");
            System.out.println("2. Remove Locker");
            System.out.println("3. Generate Report");
            System.out.println("4. Logout");
            System.out.print("Choose: ");
            int choice = in.nextInt();
            in.nextLine();

            if (choice == 1) {
                System.out.print("ID: "); int id = in.nextInt(); in.nextLine(); // 2358947
                System.out.print("College: "); String col = in.nextLine(); // IT
                System.out.print("Location: "); String loc = in.nextLine(); //buiding a*
                system.addLocker(id, col);
                
            } else if (choice == 2) {
                System.out.print("Locker number to remove: ");
                int id = in.nextInt(); in.nextLine();
                system.removeLocker(id);
                
            } else if (choice == 3) {
                System.out.println(report.viewAndGenerateReports());// it does not recall correctey* 
            } else {
                break;
            }
        }
    }
}
