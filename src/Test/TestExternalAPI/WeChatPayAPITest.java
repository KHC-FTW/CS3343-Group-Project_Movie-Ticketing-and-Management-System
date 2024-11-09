package TestExternalAPI;

import ExternalAPI.ExternalAPI;
import ExternalAPI.WeChatPayAPI;
import ExternalAPI.WeChatPayAPIFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * WeChatPayAPITest class
 * It is used to test the WeChatPayAPI class and WeChatPayAPIFactory class
 */
public class WeChatPayAPITest {
    Random random;
    WeChatPayAPIFactory weChatPayAPIFactory;

    /**
     * Set up the test environment
     * Initialize the random object with seed 10 and WeChatPayAPIFactory object
     */
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        weChatPayAPIFactory = new WeChatPayAPIFactory();
    }

    /**
     * Test the WeChatPayAPIFactory class
     * Create an WeChatPayAPI object and check if it is an instance of WeChatPayAPI
     */
    @Test
    public void testGetExternalAPI() {
        ExternalAPI externalAPI = weChatPayAPIFactory.getExternalAPI();
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof WeChatPayAPI);
    }

    /**
     * Test the WeChatPayAPIFactory class with random
     * Create an WeChatPayAPI object and check if it is an instance of WeChatPayAPI
     */
    @Test
    public void testGetExternalAPI_Random() {
        ExternalAPI externalAPI = weChatPayAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof WeChatPayAPI);
    }

    /**
     * Test the WeChatPayAPIFactory class with null random object
     * Create an WeChatPayAPI object and check if it is an instance of WeChatPayAPI
     * The random object should be generated inside the method, and the method should not throw NullPointerException
     */
    @Test
    public void testGetExternalAPI_Null_Random() {
        ExternalAPI externalAPI = weChatPayAPIFactory.getExternalAPI(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> externalAPI.doPayment(1));
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof WeChatPayAPI);
    }

    /**
     * Test if the WeChatPayAPI can do payment
     * The payment should be successful, as price of 100 should be less than the random number generated
     */
    @Test
    public void testDoPayment() {
        ExternalAPI externalAPI = weChatPayAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(externalAPI.doPayment(100));
    }

    /**
     * Test if the WeChatPayAPI can fail to do payment
     * The payment should be unsuccessful, as price of 15 should be greater than the random number generated
     */
    @Test
    public void testDoPayment_False() {
        ExternalAPI externalAPI = weChatPayAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(externalAPI.doPayment(15));
    }

}
