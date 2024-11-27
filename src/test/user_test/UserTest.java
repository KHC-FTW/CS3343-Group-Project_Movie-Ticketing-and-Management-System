package test.user_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.*;

import release.user.*;
import release.exception.ExInvalidOption;
import release.in_out.*;
import release.record.PaymentRecord;
import release.shoppingCart.ShoppingCart;

// Test method for different user type
public class UserTest {
    @Test
    public void testGetType() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        String result = user.getType();
        assertEquals("guest", result);
    }

    @Test
    public void testGetID() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        String result = user.getId();
        assertEquals("g001", result);
    }

    @Test
    public void testGetName() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        String result = user.getName();
        assertEquals("Guest", result);
    }

    @Test
    public void testGetUsername() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        String result = user.getUsername();
        assertEquals("no username", result);
    }    

    @Test
    public void testGetPassword() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        String result = user.getPassword();
        assertEquals("no password", result);
    }   

    @Test
    public void testGetAge() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        int result = user.getAge();
        assertEquals(0, result);
    }   

    @Test
    public void testSetPassword_true() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        user.setPassword("123456");
        String result = user.getPassword();
        assertEquals("123456", result);
    }  

    @Test
    public void testSetPassword_false() {
        User user = new User("guest", "g001", "Guest", "no username", "no password", 0);
        user.setPassword("123456");
        String result = user.getPassword();
        assertNotEquals("no username", "123456");
    }  

    @Test
    public void testGetOptionWithString_Guest_true() {
        User user = Guest.getInstance();
        boolean thrown = false;

        try {
            Option option = user.getOption("Login");
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testGetOptionWithString_Guest_false() {
        User user = Guest.getInstance();
        boolean thrown = false;

        try {
            Option option = user.getOption("Personal Profile");
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testGetOptionWithInt_Guest_true() {
        User user = Guest.getInstance();
        boolean thrown = false;

        try {
            Option option = user.getOption(1);
            assertEquals(true, option.getName().equals("Register"));
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testGetOptionWithInt_Guest_false() {
        User user = Guest.getInstance();
        boolean thrown = false;

        try {
            Option option = user.getOption(7);
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testGetOptionWithString_Admin_true() {
        User user = new Admin("a002", "Admin", "admin", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption("Add Default Movies");
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testGetOptionWithString_Admin_false() {
        User user = new Admin("a002", "Admin", "admin", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption("Login");
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testGetOptionWithInt_Admin_true() {
        User user = new Admin("a002", "Admin", "admin", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption(1);
            assertEquals(true, option.getName().equals("List Movies (Admin)"));
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testGetOptionWithInt_Admin_false() {
        User user = new Admin("a002", "Admin", "admin", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption(16);
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testGetOptionWithString_Member_true() {
        User user = new Member("m001", "Member", "member", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption("Buy Movie Tickets");
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testGetOptionWithString_Member_false() {
        User user = new Member("m001", "Member", "member", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption("Add Default Movies");
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testGetOptionWithInt_Member_true() {
        User user = new Member("m001", "Member", "member", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption(1);
            assertEquals(true, option.getName().equals("List Movies"));
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testGetOptionWithInt_Member_false() {
        User user = new Member("m001", "Member", "member", "password", 20);
        boolean thrown = false;

        try {
            Option option = user.getOption(10);
        } catch (ExInvalidOption e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testGetCategory() {
        User user = new Member("m001", "Member", "member", "password", 20);
        Category result = ((Member)user).getCategory();
        assertEquals(true, result != null && result.toString().equals("Adult"));
    }

    @Test
    public void testGetShoppingCart() {
        User user = new Member("m001", "Member", "member", "password", 20);
        ShoppingCart result = ((Member)user).getShoppingCart();
        assertNotNull(result);
    }

    @Test
    public void testGetPaymentRecords() {
        User user = new Member("m001", "Member", "member", "password", 20);
        List<PaymentRecord> result = ((Member)user).getPaymentRecords();
        assertNotNull(result);
    }

    @Test
    public void testMemberWithSchool() {
        User user = new Member("m001", "Member", "member", "password", 20, "NTU");
        Category result = ((Member)user).getCategory();
        assertEquals(true, result != null && result.toString().equals("Student"));
    }
}