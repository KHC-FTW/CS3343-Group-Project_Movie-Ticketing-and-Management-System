package test.helper_formatChecker_user_test;

//import org.junit.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import release.exception.*;
import release.helper.FormatChecker;

public class FormatCheckerUserTest {
    @Test
    public void testGetInstance() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        assertNotNull(formatChecker);
    }

    @Test
    public void testCheckType_member_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkType("member");
            formatChecker.checkType("admin");
            formatChecker.checkType("MeMBer");
            formatChecker.checkType("AdmiN");
        } catch (ExInvalidUserType e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testCheckType_member_false_1() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkType("guest");
        } catch (ExInvalidUserType e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckType_member_false_2() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkType("mem");
        } catch (ExInvalidUserType e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckType_member_false_3() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkType("admi");
        } catch (ExInvalidUserType e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckName_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkName("alan");
            formatChecker.checkName("alan fung");
            formatChecker.checkName("Mr.Alan");
            formatChecker.checkName("123456789012");
        } catch (ExInvalidName e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testCheckName_false_1() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkName("");
        } catch (ExInvalidName e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckName_false_2() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkName("1234567890123");
        } catch (ExInvalidName e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testcheckUsername_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkUsername("alan");
            formatChecker.checkUsername("alanfung");
            formatChecker.checkUsername("Mr.Alan");
            formatChecker.checkUsername("123456789012");
        } catch (ExInvalidUsername e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testcheckUsername_false_1() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkUsername("");
        } catch (ExInvalidUsername e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testcheckUsername_false_2() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkUsername("alan fung");
        } catch (ExInvalidUsername e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testcheckUsername_false_3() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkUsername("1234567890123");
        } catch (ExInvalidUsername e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckPassword_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkPassword("password");
            formatChecker.checkPassword("123456");
            formatChecker.checkPassword("123456789012345");
        } catch (ExInvalidPassword e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testCheckPassword_false_1() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkPassword("");
        } catch (ExInvalidPassword e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckPassword_false_2() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkPassword("my password");
        } catch (ExInvalidPassword e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckPassword_false_3() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkPassword("1234567890123456");
        } catch (ExInvalidPassword e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckAge_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkAge("20");
            formatChecker.checkAge("1");
            formatChecker.checkAge("125");
        } catch (ExInvalidAge e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testCheckAge_false_1() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkAge("s");
        } catch (ExInvalidAge e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    } 

    @Test
    public void testCheckAge_false_2() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkAge("0");
        } catch (ExInvalidAge e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckAge_false_3() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkAge("126");
        } catch (ExInvalidAge e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckSchool_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkSchool("CityU");
            formatChecker.checkSchool("HKU");
            formatChecker.checkSchool("CUHK");
            formatChecker.checkSchool("HKUST");
        } catch (ExInvalidSchool e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testCheckSchool_false() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkSchool("");
        } catch (ExInvalidSchool e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckSearch_true() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkSearch("alan");
            formatChecker.checkSearch("alan fung");
            formatChecker.checkSearch("Mr.Alan");
            formatChecker.checkSearch("123456789012");
        } catch (ExInvalidSearch e) {
            thrown = true;
        }

        assertEquals(false, thrown);
    }

    @Test
    public void testCheckSearch_false_1() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkSearch("");
        } catch (ExInvalidSearch e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }

    @Test
    public void testCheckSearch_false_2() {
        FormatChecker formatChecker = FormatChecker.getInstance();
        boolean thrown = false;

        try {
            formatChecker.checkSearch("1234567890123");
        } catch (ExInvalidSearch e) {
            thrown = true;
        }

        assertEquals(true, thrown);
    }
}