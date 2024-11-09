package TestExternalAPI;

import ExternalAPI.ExternalAPI;
import ExternalAPI.OctopusAPI;
import ExternalAPI.OctopusAPIFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * OctopusAPITest class
 * It is used to test the OctopusAPI class and OctopusAPIFactory class
 */
public class OctopusAPITest {
    Random random;
    OctopusAPIFactory octopusAPIFactory;

    /**
     * Set up the test environment
     * Initialize the random object with seed 10 and OctopusAPIFactory object
     */
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        octopusAPIFactory = new OctopusAPIFactory();

    }

    /**
     * Test the OctopusAPIFactory class
     * Create an OctopusAPI object and check if it is an instance of OctopusAPI
     */
    @Test
    public void testOctopusFactory() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI();
        Assertions.assertTrue(octopusAPI instanceof OctopusAPI);
    }

    /**
     * Test the OctopusAPIFactory class with random object
     * Create an OctopusAPI object and check if it is an instance of OctopusAPI
     */
    @Test
    public void testOctopusFactory_Random() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(octopusAPI instanceof OctopusAPI);
    }

    /**
     * Test the OctopusAPIFactory class with null random object
     * Create an OctopusAPI object and check if it is an instance of OctopusAPI
     * The random object should be generated inside the method, and the method should not throw NullPointerException
     */
    @Test
    public void testOctopusFactory_Random_Null() {
        ExternalAPI externalAPI = octopusAPIFactory.getExternalAPI(null);
        Assertions.assertDoesNotThrow(() -> externalAPI.doPayment(1));
        // Test if there is no NullPointerException
        externalAPI.doPayment(1);
        Assertions.assertTrue(externalAPI instanceof OctopusAPI);
    }

    /**
     * Test the doPayment method in OctopusAPI class
     * Create an OctopusAPI object and check if the doPayment method returns true
     * By trial and error, the random number generated is 113, which is greater than or equal to the price 100
     */
    @Test
    public void testDoPayment() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(octopusAPI.doPayment(100));
    }

    /**
     * Test the doPayment method in OctopusAPI class
     * Create an OctopusAPI object and check if the doPayment method returns false
     * By trial and error, the random number generated is 3, which is less than the price 15
     */
    @Test
    public void testDoPayment_Fail() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(octopusAPI.doPayment(15));
    }
}
