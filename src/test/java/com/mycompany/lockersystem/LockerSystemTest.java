package com.mycompany.lockersystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class LockerSystemTest {

    LockerSystem system;
    Student normal1;
    Student normal2;
    Student special;
    Staff admin;


    @BeforeEach
    public void setup() {
        system = new LockerSystem(5);

        // normal student
        normal1 = new Student("2301111", "pass1", "Engineering");
        normal1.setSpecialNeeds(false);

        // another normal student
        normal2 = new Student("2302222", "pass2", "Engineering");
        normal2.setSpecialNeeds(false);

        // special needs student
        special = new Student("2303333", "pass3", "Engineering");
        special.setSpecialNeeds(true);

        // admin
        admin = new Staff("admin", "admin123");

        system.addUser(normal1);
        system.addUser(normal2);
        system.addUser(special);
        system.addUser(admin);
    }

    @Test
    public void testLoginSuccess() {
        User user = system.login("2301111", "pass1");
        assertNotNull(user, "User should login successfully");

    }

    @Test
    public void testLoginFail() {
        User user = system.login("2301111", "wrongpass");
        assertNull(user, "Login must fail with wrong password");

    }

    @Test
    public void testSpecialNeedsReservation() {
        boolean result = system.reserveLocker(1, special);
        assertTrue(result, "Special needs student must succeed with priority");

    }

    @Test
    public void testNormalStudentBlockedDueToSpecialNeeds() {
        // special needs has NOT reserved yet → normal should be blocked
        boolean result = system.reserveLocker(2, normal1);
        assertFalse(result, "Normal student should NOT reserve because special needs student exists");

    }

    @Test
    public void testNormalReservationAfterSpecialNeedsReserved() {
        // special needs reserves first
        system.reserveLocker(1, special);

        // now normal student can reserve
        boolean result = system.reserveLocker(2, normal1);
         assertTrue(result, "Normal student should reserve once special needs already has a locker");
    }
}
