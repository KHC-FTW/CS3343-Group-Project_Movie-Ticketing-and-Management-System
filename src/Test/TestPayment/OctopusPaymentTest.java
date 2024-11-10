package TestPayment;

import ExternalAPI.ExternalAPI;
import ExternalAPI.ExternalAPIFactory;
import ExternalAPI.OctopusAPIFactory;
import Payment.OctopusPaymentFactory;
import Payment.PaymentStatus;
import Payment.Payment;
import Payment.OctopusPayment;
import Payment.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * OctopusPaymentTest class<br>
 * It is used to test the OctopusPayment class and OctopusPaymentFactory class
 */
public class OctopusPaymentTest {
    OctopusPaymentFactory octopusPaymentFactory;
    ExternalAPIFactory octopusAPIFactory;

    /**
     * Set up the test environment<br>
     * Initialize the OctopusPaymentFactory object, which is used for creating OctopusPayment object
     */
    @BeforeEach
    public void setUp() {
        octopusAPIFactory = new OctopusAPIFactory();
        octopusPaymentFactory = new OctopusPaymentFactory();
    }

    /**
     * Test the OctopusPaymentFactory class<br>
     * Create an OctopusPayment object using OctopusPaymentFactory and check if it is an instance of OctopusPayment
     */
    @Test
    public void testOctopusPaymentFactory() {
        Payment payment = octopusPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof OctopusPayment);
    }

    /**
     * Test the OctopusPaymentFactory class with ExternalAPI object<br>
     * Create an OctopusPayment object using OctopusPaymentFactory and check if it is an instance of OctopusPayment
     */
    @Test
    public void testOctopusPaymentFactory_ExternalAPI() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI(new Random(10));
        Payment payment = octopusPaymentFactory.createPaymentMethod(octopusAPI);
        Assertions.assertTrue(payment instanceof OctopusPayment);
    }

    /**
     * Test the OctopusPaymentFactory class with null ExternalAPI object<br>
     * Create an OctopusPayment object using OctopusPaymentFactory and check if it is an instance of OctopusPayment<br>
     * The method should not throw NullPointerException
     */
    @Test
    public void testOctopusPaymentFactory_ExternalAPI_Null() {
        Payment payment = octopusPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertTrue(payment instanceof OctopusPayment);
    }

    /**
     * Test the getPaymentType method in OctopusPayment class<br>
     * Create an OctopusPayment object and check if the payment type is PaymentType.OCTOPUS.
     */
    @Test
    public void testOctopusGetPaymentType() {
        Payment octopusPayment = octopusPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentType.OCTOPUS, octopusPayment.getPaymentType());
    }

    /**
     * Test the doPayment method in OctopusPayment class<br>
     * Create an OctopusPayment object and check if the doPayment method returns true<br>
     * The OctopusPaymentStub class is used to override the doPayment method in OctopusPayment class, to simulate the payment process without using the Octopus card API
     */
    @Test
    public void testOctopusDoPayment() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI(new Random(10));
        Payment octopusPayment = octopusPaymentFactory.createPaymentMethod(octopusAPI);
        Assertions.assertTrue(octopusPayment.doPayment(100));
        Assertions.assertEquals(PaymentStatus.SUCCESS, octopusPayment.getPaymentStatus());
    }

    /**
     * Test the doPayment method in OctopusPayment class<br>
     * Create an OctopusPayment object and check if the doPayment method returns false<br>
     * The OctopusPaymentStub class is used to override the doPayment method in OctopusPayment class, to simulate the payment process without using the Octopus card API
     */
    @Test
    public void testOctopusDoPayment_False() {
        ExternalAPI octopusAPI = octopusAPIFactory.getExternalAPI(new Random(10));
        Payment octopusPayment = octopusPaymentFactory.createPaymentMethod(octopusAPI);
        Assertions.assertFalse(octopusPayment.doPayment(15));
        Assertions.assertEquals(PaymentStatus.FAIL, octopusPayment.getPaymentStatus());
    }

    /**
     * Test the getPaymentStatus method in OctopusPayment class<br>
     * Create an OctopusPayment object and check if the payment status is PaymentStatus.NOT_PROCEED
     */
    @Test
    public void testOctopusGetPaymentStatus() {
        Payment octopusPayment = octopusPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(octopusPayment.getPaymentStatus(), PaymentStatus.NOT_PROCEED);
    }
}
