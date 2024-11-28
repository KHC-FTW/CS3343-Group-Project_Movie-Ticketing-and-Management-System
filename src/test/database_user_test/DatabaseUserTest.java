package test.database_user_test;

//import org.junit.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.database.Database;
import release.user.User;
import release.user.Member;
import release.user.Admin;

public class DatabaseUserTest {
    @Test
    public void testGetInstance() {
        Database database = Database.getInstance();
        assertNotNull(database);
    }

    @Test
    public void testGetSmallestMemberId() {
        Database database = Database.getInstance();
        String smallestMemberId = database.getSmallestMemberId();
        assertEquals("m001", smallestMemberId);
    }

    @Test
    public void testGetSmallestAdminId() {
        Database database = Database.getInstance();
        String smallestAdminId = database.getSmallestAdminId();
        assertEquals("a001", smallestAdminId);
    }

    @Test
    public void testGetUserById_true() {
        Database database = Database.getInstance();
        assertEquals("a000", database.getUserById("a000").getId());
    }

    @Test
    public void testGetUserById_false() {
        Database database = Database.getInstance();
        assertEquals(null, database.getUserById("a001"));
    }

    @Test
    public void isDuplicatedUsername_true() {
        Database database = Database.getInstance();
        assertEquals(true, database.isDuplicatedUsername("admin"));
    }

    @Test
    public void isDuplicatedUsername_false() {
        Database database = Database.getInstance();
        assertEquals(false, database.isDuplicatedUsername("newUsername"));
    }

    @Test
    public void testAddMember_true() {
        Database database = Database.getInstance();
        User member = new Member(database.getSmallestMemberId(), "name", "username", "password", 20);
        boolean result = database.addMember(member);
        assertEquals(true, result);
        database.delUser(member.getId());
    }

    @Test
    public void testAddMember_false() {
        Database database = Database.getInstance();
        User member1 = new Member(database.getSmallestMemberId(), "name", "username1", "password", 20);
        User member2 = new Member(database.getSmallestMemberId(), "name", "username1", "password", 20);
        database.addMember(member1);
        boolean result = database.addMember(member2);
        assertEquals(false, result);
        database.delUser(member1.getId());
    }

    @Test
    public void testDelUser_true() {
        Database database = Database.getInstance();
        database.addMember(new Member(database.getSmallestMemberId(), "name", "username1", "password", 20));
        assertEquals("name", database.searchUser("m001").get(0).getName());
        database.delUser("m001");
    }

    @Test
    public void testDelUser_false() {
        Database database = Database.getInstance();
        User user = database.delUser("a001");
        assertEquals(null, user);
    }

    @Test
    public void testAddAdmin_true() {
        Database database = Database.getInstance();
        User admin = new Admin(database.getSmallestAdminId(), "name", "username", "password", 20);
        boolean result = database.addAdmin(admin);
        assertEquals(true, result);
        database.delUser(admin.getId());
    }

    @Test
    public void testAddAdmin_false() {
        Database database = Database.getInstance();
        User admin = new Admin(database.getSmallestAdminId(), "name", "admin", "password", 20);
        boolean result = database.addAdmin(admin);
        assertEquals(false, result);
    }

    @Test
    public void testSearchUser_true() {
        Database database = Database.getInstance();
        User member1 = new Member(database.getSmallestMemberId(), "mem1", "username1", "password", 20);
        User member2 = new Member(database.getSmallestMemberId(), "mem2", "username2", "password", 20);
        User member3 = new Member(database.getSmallestMemberId(), "mem3", "username3", "password", 20);
        database.addMember(member1);
        database.addMember(member2);
        database.addMember(member3);
        assertEquals(3, database.searchUser("mem").size());
        database.delUser(member1.getId());
        database.delUser(member2.getId());
        database.delUser(member3.getId());
    }

    @Test
    public void testSearchUser_false() {
        Database database = Database.getInstance();
        assertEquals(0, database.searchUser("mem").size());
    }

    @Test
    public void testVerifyUser_true() {
        Database database = Database.getInstance();
        assertEquals("a000", database.verifyUser("admin", "password").getId());
    }

    @Test
    public void testVerifyUser_false() {
        Database database = Database.getInstance();
        assertEquals(null, database.verifyUser("admin", "wrongPassword"));
    }
}