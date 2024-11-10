package TestExternalAPI;

import ExternalAPI.ExternalAPI;
import ExternalAPI.PayMeAPI;
import ExternalAPI.PayMeAPIFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * PayMeAPITest class
 * It is used to test the PayMeAPI class and PayMeAPIFactory class
 */
public class PayMeAPITest {
    Random random;
    PayMeAPIFactory payMeAPIFactory;

    /**
     * Set up the test environment
     * Initialize the random object with seed 10 and PayMeAPIFactory object
     */
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        payMeAPIFactory = new PayMeAPIFactory();
    }

    /**
     * Test the PayMeAPIFactory class
     * Create an PayMeAPI object and check if it is an instance of PayMeAPI
     */
    @Test
    public void testGetExternalAPI() {
        ExternalAPI externalAPI = payMeAPIFactory.getExternalAPI();
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof PayMeAPI);
    }

    /**
     * Test the PayMeAPIFactory class with random object
     * Create an PayMeAPI object and check if it is an instance of PayMeAPI
     */
    @Test
    public void testGetExternalAPI_Random() {
        ExternalAPI externalAPI = payMeAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof PayMeAPI);
    }

    /**
     * Test the PayMeAPIFactory class with null random object
     * Create an PayMeAPI object and check if it is an instance of PayMeAPI
     * The random object should be generated inside the method, and the method should not throw NullPointerException
     */
    @Test
    public void testGetExternalAPI_Null_Random() {
        ExternalAPI externalAPI = payMeAPIFactory.getExternalAPI(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> externalAPI.doPayment(1));
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof PayMeAPI);
    }

    /**
     * Test the doPayment method of PayMeAPI
     * The method should return true, as the price is less than the random number generated
     */
    @Test
    public void testDoPayment() {
        ExternalAPI externalAPI = payMeAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(externalAPI.doPayment(100));
    }

    /**
     * Test the doPayment method of PayMeAPI
     * The method should return false, as the price is greater than the random number generated
     */
    @Test
    public void testDoPayment_False() {
        ExternalAPI externalAPI = payMeAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(externalAPI.doPayment(15));
    }
}
