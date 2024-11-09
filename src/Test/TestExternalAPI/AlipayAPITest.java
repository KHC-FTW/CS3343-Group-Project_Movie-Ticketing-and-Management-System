package TestExternalAPI;

import ExternalAPI.AlipayAPI;
import ExternalAPI.AlipayAPIFactory;
import ExternalAPI.ExternalAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * AlipayAPITest class
 * Unit test for AlipayAPI
 */
public class AlipayAPITest {
    Random random;
    AlipayAPIFactory alipayAPIFactory;

    /**
     * Set up the test environment
     */
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        alipayAPIFactory = new AlipayAPIFactory();
    }

    /**
     * Test if the AlipayAPIFactory can create an instance of AlipayAPI
     */
    @Test
    public void testGetExternalAPI() {
        ExternalAPI externalAPI = alipayAPIFactory.getExternalAPI();
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof AlipayAPI);
    }

    /**
     * Test if the AlipayAPIFactory can create an instance of AlipayAPI with a random object
     */
    @Test
    public void testGetExternalAPI_Random() {
        ExternalAPI externalAPI = alipayAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof AlipayAPI);
    }

    /**
     * Test if the AlipayAPIFactory can create an instance of AlipayAPI with a null random object
     * The random object should be generated inside the method, and the method should not throw NullPointerException
     */
    @Test
    public void testGetExternalAPI_Random_Num() {
        ExternalAPI externalAPI = alipayAPIFactory.getExternalAPI(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> externalAPI.doPayment(1));
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof AlipayAPI);
    }

    /**
     * Test if the AlipayAPI can do payment successfully
     * The payment should be successful, as price of 100 should be less than the random number generated
     */
    @Test
    public void testDoPayment() {
        ExternalAPI externalAPI = alipayAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(externalAPI.doPayment(100));
    }

    /**
     * Test if the AlipayAPI can do payment unsuccessfully
     * The payment should not be successful, as price of 15 should be larger than the random number generated
     */
    @Test
    public void testDoPayment_False() {
        ExternalAPI externalAPI = alipayAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(externalAPI.doPayment(15));
    }
}
