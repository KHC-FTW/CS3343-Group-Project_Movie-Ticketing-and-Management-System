package test.user_test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import release.user.*;

public class CategoryTest {
    @Test
    public void testChild() {
        Child child = Child.getInstance();
        boolean result = child.getDiscount() == 0.6 && child.toString().equals("Child");
        assertEquals(true, result); 
    }

    @Test
    public void testAdult() {
        Adult adult = Adult.getInstance();
        boolean result = adult.getDiscount() == 1.0 && adult.toString().equals("Adult");
        assertEquals(true, result); 
    }

    @Test
    public void testElder() {
        Elder elder = Elder.getInstance();
        boolean result = elder.getDiscount() == 0.5 && elder.toString().equals("Elder");
        assertEquals(true, result); 
    }

    @Test
    public void testStudent() {
        Student student = Student.getInstance();
        boolean result = student.getDiscount() == 0.8 && student.toString().equals("Student");
        assertEquals(true, result); 
    }
}
