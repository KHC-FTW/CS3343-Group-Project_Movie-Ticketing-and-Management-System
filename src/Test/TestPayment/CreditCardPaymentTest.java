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

public class CreditCardPaymentTest {
    CreditCardPaymentFactory creditCardPaymentFactory;
    CreditCardAPIFactory creditCardAPIFactory;
    
    @BeforeEach
    public void setUp() {
        creditCardPaymentFactory = new CreditCardPaymentFactory();
        creditCardAPIFactory = new CreditCardAPIFactory();
    }
    
    @Test
    public void testCreditCardPaymentFactory() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof CreditCardPayment);
    }
    
    @Test
    public void testCreditCardPaymentFactory_ExternalAPI() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI();
        Payment payment = creditCardPaymentFactory.createPaymentMethod(creditCardAPI);
        Assertions.assertTrue(payment instanceof CreditCardPayment);
    }
    
    @Test
    public void testCreditCardPaymentFactory_ExternalAPI_Null() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertTrue(payment instanceof CreditCardPayment);
    }
    
    @Test
    public void testDoPayment() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI(new Random(10));
        Payment payment = creditCardPaymentFactory.createPaymentMethod(creditCardAPI);
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.SUCCESS);
    }
    
    @Test
    public void testDoPayment_False() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI(new Random(10));
        Payment payment = creditCardPaymentFactory.createPaymentMethod(creditCardAPI);
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.FAIL);
    }
    
    @Test
    public void testGetPaymentType() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentType(), PaymentType.CREDIT_CARD);
    }
    
    @Test
    public void testGetPaymentStatus() {
        Payment payment = creditCardPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.NOT_PROCEED);
    }
}
