package TestPayment;

import ExternalAPI.ExternalAPI;
import ExternalAPI.CreditCardAPIFactory;
import Payment.CreditCardPayment;
import Payment.CreditCardPaymentFactory;
import Payment.Payment;
import Payment.PaymentType;
import Payment.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * CreditCardPaymentTest class<br>
 * It is used to test the CreditCardPayment class and CreditCardPaymentFactory class
 */
public class CreditCardPaymentTest {
    CreditCardPaymentFactory creditCardPaymentFactory;
    CreditCardAPIFactory creditCardAPIFactory;
    
    /**
     * Set up the test environment
     */
    @BeforeEach
    public void setUp() {
        creditCardPaymentFactory = new CreditCardPaymentFactory();
        creditCardAPIFactory = new CreditCardAPIFactory();
    }
    
    /**
     * Test if the CreditCardPaymentFactory can create an instance of CreditCardPayment
     */
    @Test
    public void testCreditCardPaymentFactory() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof CreditCardPayment);
    }
    
    /**
     * Test if the CreditCardPaymentFactory can create an instance of CreditCardPayment with an ExternalAPI object
     */
    @Test
    public void testCreditCardPaymentFactory_ExternalAPI() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI();
        Payment payment = creditCardPaymentFactory.createPaymentMethod(creditCardAPI);
        Assertions.assertTrue(payment instanceof CreditCardPayment);
    }
    
    /**
     * Test if the CreditCardPaymentFactory can create an instance of CreditCardPayment with a null ExternalAPI object<br>
     * The method should not throw NullPointerException
     */
    @Test
    public void testCreditCardPaymentFactory_ExternalAPI_Null() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertTrue(payment instanceof CreditCardPayment);
    }
    
    /**
     * Test if the CreditCardPayment can do payment.<br>
     * The payment should be successful, and the payment status should be SUCCESS.
     */
    @Test
    public void testDoPayment() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI(new Random(10));
        Payment payment = creditCardPaymentFactory.createPaymentMethod(creditCardAPI);
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.SUCCESS);
    }
    
    /**
     * Test if the CreditCardPayment can fail to do the payment.<br>
     * The payment should fail, and the payment status should be FAIL.
     */
    @Test
    public void testDoPayment_False() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI(new Random(10));
        Payment payment = creditCardPaymentFactory.createPaymentMethod(creditCardAPI);
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.FAIL);
    }

    /**
     * Test the getPaymentType method in CreditCardPayment class<br>
     * The method should return PaymentType.CREDIT_CARD
     */
    @Test
    public void testGetPaymentType() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentType(), PaymentType.CREDIT_CARD);
    }
    
    /**
     * Test the getPaymentStatus method in CreditCardPayment class<br>
     * The method should return PaymentStatus.NOT_PROCEED
     */
    @Test
    public void testGetPaymentStatus() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.NOT_PROCEED);
    }
}
