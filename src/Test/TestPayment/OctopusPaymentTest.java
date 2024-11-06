package TestPayment;

import Payment.OctopusPaymentFactory;
import Payment.Payment;
import Payment.OctopusPayment;
import Payment.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * OctopusPaymentTest class
 * It is used to test the OctopusPayment class and OctopusPaymentFactory class
 */
public class OctopusPaymentTest {
    OctopusPaymentFactory octopusPaymentFactory;

    /**
     * Set up the test environment
     * Initialize the OctopusPaymentFactory object, which is used for creating OctopusPayment object
     */
    @BeforeEach
    public void setUp() {
        octopusPaymentFactory = new OctopusPaymentFactory();
    }

    /**
     * Test the OctopusPaymentFactory class
     * Create an OctopusPayment object using OctopusPaymentFactory and check if it is an instance of OctopusPayment
     */
    @Test
    public void testOctopusPaymentFactory() {
        Payment payment = octopusPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof OctopusPayment);
    }

    /**
     * Test the getPaymentType method in OctopusPayment class
     * Create an OctopusPayment object and check if the payment type is PaymentType.OCTOPUS
     */
    @Test
    public void testOctopusGetPaymentType() {
        Payment octopusPayment = octopusPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentType.OCTOPUS, octopusPayment.getPaymentType());
    }

    /**
     * Test the doPayment method in OctopusPayment class
     * Create an OctopusPayment object and check if the doPayment method returns true
     * The OctopusPaymentStub class is used to override the doPayment method in OctopusPayment class, to simulate the payment process without using the Octopus card API
     */
    @Test
    public void testOctopusDoPayment() {
        class OctopusPaymentStub extends OctopusPayment {
            @Override
            public boolean doPayment(int price) {
                return true;
            }
        }
        OctopusPayment octopusPayment = new OctopusPaymentStub();
        Assertions.assertTrue(octopusPayment.doPayment(100));
    }

    /**
     * Test the doPayment method in OctopusPayment class
     * Create an OctopusPayment object and check if the doPayment method returns false
     * The OctopusPaymentStub class is used to override the doPayment method in OctopusPayment class, to simulate the payment process without using the Octopus card API
     */
    @Test
    public void testOctopusDoPayment_False() {
        class OctopusPaymentStub extends OctopusPayment {
            @Override
            public boolean doPayment(int price) {
                return false;
            }
        }
        OctopusPayment octopusPayment = new OctopusPaymentStub();
        Assertions.assertFalse(octopusPayment.doPayment(100));
    }
}
