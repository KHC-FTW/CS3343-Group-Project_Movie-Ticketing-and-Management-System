package TestPayment;

import ExternalAPI.PayMeAPIFactory;
import Payment.PayMePayment;
import Payment.Payment;
import Payment.PayMePaymentFactory;
import Payment.PaymentStatus;
import Payment.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class PayMePaymentTest {
    PayMePaymentFactory payMePaymentFactory;
    PayMeAPIFactory payMeAPIFactory;
    
    @BeforeEach
    public void setUp() {
        payMePaymentFactory = new PayMePaymentFactory();
        payMeAPIFactory = new PayMeAPIFactory();
    }
    
    @Test
    public void testPayMePaymentFactory() {
        Payment payment = payMePaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof PayMePayment);
    }
    
    @Test
    public void testPayMePaymentFactory_ExternalAPI() {
        Payment payment = payMePaymentFactory.createPaymentMethod(payMeAPIFactory.getExternalAPI());
        Assertions.assertTrue(payment instanceof PayMePayment);
    }
    
    @Test
    public void testPayMePaymentFactory_ExternalAPI_Null() {
        Payment payment = payMePaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertTrue(payment instanceof PayMePayment);
    }
    
    @Test
    public void testDoPayment() {
        Payment payment = payMePaymentFactory.createPaymentMethod(
                payMeAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.SUCCESS);
    }
    
    @Test
    public void testDoPayment_False() {
        Payment payment = payMePaymentFactory.createPaymentMethod(
                payMeAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.FAIL);
    }
    
    @Test
    public void testGetPaymentType() {
        Payment payment = payMePaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentType(), PaymentType.PAYME);
    }
    
    @Test
    public void testGetPaymentStatus() {
        Payment payment = payMePaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.NOT_PROCEED);
    }
    
}

