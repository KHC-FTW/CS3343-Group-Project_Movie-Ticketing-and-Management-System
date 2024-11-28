package test.user_test;

//import org.junit.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import release.exception.ExDuplicateUsername;
import release.exception.ExUserNotExist;
import release.user.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserCenterTest {
    @Test
    public void testGetInstance() {
        UserCenter userCenter = UserCenter.getInstance();
        assertNotNull(userCenter);
    }

    @Test
    public void testGetCurrentUser() {
        UserCenter userCenter = UserCenter.getInstance();
        User guest = Guest.getInstance();
        User currentUser = userCenter.getCurrentUser();
        assertEquals("Guest", currentUser.getName());
    }


    @Test
    public void testLogin_true() {
        UserCenter userCenter = UserCenter.getInstance();
        try {
            userCenter.login("admin", "password");
        } catch (ExUserNotExist e) {

        }
        User currentUser = userCenter.getCurrentUser();
        userCenter.logout();
        assertEquals("Master Admin", currentUser.getName());
    }

    @Test
    public void testLogin_false() {
        UserCenter userCenter = UserCenter.getInstance();
        try {
            userCenter.login("invalidUser", "password");
        } catch (ExUserNotExist e) {

        }
        User currentUser = userCenter.getCurrentUser();
        assertNotEquals("invalidUser", currentUser.getName());
    }

    @Test
    public void testLogout() {
        UserCenter userCenter = UserCenter.getInstance();
        try {
            userCenter.login("admin", "password");
        } catch (ExUserNotExist e) {

        }
        userCenter.logout();
        User currentUser = userCenter.getCurrentUser();
        assertEquals("Guest", currentUser.getName());
    }

    @Test
    public void testDelUser_true() {
        UserCenter userCenter = UserCenter.getInstance();
        User user = null;

        try {
            User newMember = userCenter.addMember("alan", "username", "123456", 20);
            user = userCenter.delUser("m001");
        } catch (Exception e) {}

        assertEquals("alan", user.getName());
    }

    @Test
    public void testDelUser_false_ExDeleteSelf() {
        UserCenter userCenter = UserCenter.getInstance();
        User user = null;

        try {
            userCenter.login("admin", "password");
            user = userCenter.delUser("a000");
        } catch (Exception e) {}

        assertEquals(null, user);
    }

    @Test
    public void testDelUser_false_ExUserNotExist() {
        UserCenter userCenter = UserCenter.getInstance();
        User user = null;

        try {
            user = userCenter.delUser("a002");
        } catch (Exception e) {}

        assertEquals(null, user);
    }

    @Test
    public void testRegister_true() {
        UserCenter userCenter = UserCenter.getInstance();
        try {
            userCenter.register("alan", "username", "password", 20);
        } catch (ExDuplicateUsername e) {}

        User currentUser = userCenter.getCurrentUser();
        assertEquals("alan", currentUser.getName());
        
        userCenter.logout();
        try {
            userCenter.delUser(currentUser.getId());
        } catch (Exception e) {}
    }

    @Test
    public void testRegister_false() {
        UserCenter userCenter = UserCenter.getInstance();

        try {
            userCenter.register("alan", "admin", "password", 20);
        } catch (ExDuplicateUsername e) {}

        User currentUser = userCenter.getCurrentUser();
        assertNotEquals("alan", currentUser.getName());
    }

    @Test
    public void testRegisterWithSchool_true() {
        UserCenter userCenter = UserCenter.getInstance();
        try {
            userCenter.register("alan", "username", "123456", 20, "CityU");
        } catch (ExDuplicateUsername e) {}

        User currentUser = userCenter.getCurrentUser();
        assertEquals(true, currentUser.getName().equals("alan") &&
        ((Member)currentUser).getCategory().toString().equals("Student"));
        
        userCenter.logout();
        try {
            userCenter.delUser(currentUser.getId());
        } catch (Exception e) {}
    }

    @Test
    public void testRegisterWithSchool_false() {
        UserCenter userCenter = UserCenter.getInstance();

        try {
            userCenter.register("alan", "admin", "123456", 20, "CityU");
        } catch (ExDuplicateUsername e) {}

        User currentUser = userCenter.getCurrentUser();
        assertEquals(false, currentUser.getName().equals("alan") &&
        ((Member)currentUser).getCategory().toString().equals("Student"));
    }

    @Test
    public void testGetUserPassword_true() {
        UserCenter userCenter = UserCenter.getInstance();
        String password = null;

        try {
            password = userCenter.getUserPassword("admin");
        } catch (ExUserNotExist e) {}

        assertEquals("password", password);
    }

    @Test
    public void testGetUserPassword_false() {
        UserCenter userCenter = UserCenter.getInstance();
        String password = null;
        
        try {
            password = userCenter.getUserPassword("member");
        } catch (ExUserNotExist e) {}

        assertEquals(null, password);
    }

    @Test
    public void testAddMember_true() {
        UserCenter userCenter = UserCenter.getInstance();
        User newMember = null;

        try {
            newMember = userCenter.addMember("alan", "username", "123456", 20);
        } catch (ExDuplicateUsername e) {}
        assertEquals("alan", newMember.getName());

        try {
            userCenter.delUser(newMember.getId());
        } catch (Exception e) {}
    }

    @Test
    public void testAddMember_false() {
        UserCenter userCenter = UserCenter.getInstance();
        User newMember = null;

        try {
            newMember = userCenter.addMember("alan", "admin", "password", 20);
        } catch (ExDuplicateUsername e) {}

        assertEquals(null, newMember);
    }

    @Test
    public void testAddMemberWithSchool_true() {
        UserCenter userCenter = UserCenter.getInstance();
        User newMember = null;

        try {
            newMember = userCenter.addMember("alan", "username", "123456", 20, "CityU");
        } catch (ExDuplicateUsername e) {}

        assertEquals(true, newMember.getName().equals("alan") &&
        ((Member)newMember).getCategory().toString().equals("Student"));

        try {
            userCenter.delUser(newMember.getId());
        } catch (Exception e) {}
    }

    @Test
    public void testAddMemberWithSchool_false() {
        UserCenter userCenter = UserCenter.getInstance();
        User newMember = null;

        try {
            newMember = userCenter.addMember("alan", "admin", "password", 20, "CityU");
        } catch (ExDuplicateUsername e) {}
        
        assertEquals(null, newMember);
    }

    @Test
    public void testAddAdmin_true() {
        UserCenter userCenter = UserCenter.getInstance();
        User newAdmin = null;

        try {
            newAdmin = userCenter.addAdmin("alan", "username", "123456", 20);
        } catch (ExDuplicateUsername e) {}
        assertEquals("alan", newAdmin.getName());

        try {
            userCenter.delUser(newAdmin.getId());
        } catch (Exception e) {}
    }

    @Test
    public void testAddAdmin_false() {
        UserCenter userCenter = UserCenter.getInstance();
        User newAdmin = null;

        try {
            newAdmin = userCenter.addAdmin("alan", "admin", "password", 20);
        } catch (ExDuplicateUsername e) {}

        assertEquals(null, newAdmin);
    }

    @Test
    public void testSearchUser_true() {
        UserCenter userCenter = UserCenter.getInstance();
        boolean thrown = false;
        try {
            userCenter.searchUser("admin");
        } catch (Exception e) {
            thrown = true;
        }
        assertEquals(false, thrown);
    }

    @Test
    public void testSearchUser_false() {
        UserCenter userCenter = UserCenter.getInstance();
        boolean thrown = false;
        try {
            userCenter.searchUser("invalidUser");
        } catch (Exception e) {
            thrown = true;
        }
        assertEquals(true, thrown);
    }
}