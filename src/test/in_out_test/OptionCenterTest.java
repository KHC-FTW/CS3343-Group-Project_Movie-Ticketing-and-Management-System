package test.in_out_test;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import release.in_out.OptionCenter;
import release.user.Guest;
import release.user.Member;
import release.user.Admin;

public class OptionCenterTest {
    @Test
    public void testGetInstance() {
        OptionCenter optionCenter = OptionCenter.getInstance();
        assertEquals(OptionCenter.class, optionCenter.getClass());
    }

    @Test
    public void testGetOptions_Guest() {
        OptionCenter optionCenter = OptionCenter.getInstance();
        assertEquals(7, optionCenter.getOptions(Guest.getInstance()).size());
    }

    @Test
    public void testGetOptions_Member() {
        OptionCenter optionCenter = OptionCenter.getInstance();
        Member member = new Member("m001", "name", "username", "password", 20);
        assertEquals(10, optionCenter.getOptions(member).size());
    }

    @Test
    public void testGetOptions_Admin() {
        OptionCenter optionCenter = OptionCenter.getInstance();
        Admin admin = new Admin("a001", "name", "username", "password", 20);
        assertEquals(16, optionCenter.getOptions(admin).size());
    }
}