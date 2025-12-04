/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lockersystem;

/**
 *
 * @author HP
 */

import java.util.List;


public class ReportSystemController {
    private LockerSystem lockerSystem;

    public ReportSystemController(LockerSystem lockerSystem) {
        this.lockerSystem = lockerSystem;
    }

    public String viewAndGenerateReports() {
        List<Locker> lockers = lockerSystem.getAllLockers();
        return generateReport(lockers);
    }

    private String generateReport(List<Locker> lockers) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Locker Assignment Report ===\n");

        for (Locker locker : lockers) {
            
            if (locker.isReserved()) {
                
                // studentId من locker
                String studentId = locker.getReservedBy();
                //retrive real user 
                User user = lockerSystem.getUserById(studentId);
                String college = "N/A";
                
                if (user instanceof Student) {
                college = ((Student) user).getCollege();
                }
               
                sb.append("Locker ")
                  .append(locker.getId())
                  .append(" -> Reserved by: ")
                  .append(studentId)
                  .append(" | College: ")
                  .append(locker.getReservedBy())
                  .append("\n");
                
                
            } else {
                sb.append("Locker ")
                  .append(locker.getId())
                  .append(" -> Available\n");
            }
        }
        return sb.toString();
    }
}