package TestPayment;

import Payment.Payment;
import Payment.PaymentStatus;
import Payment.PaymentType;
import ExternalAPI.ExternalAPI;
import ExternalAPI.AlipayAPIFactory;
import Payment.AlipayPayment;
import Payment.AlipayPaymentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AlipayPaymentTest {
    AlipayPaymentFactory alipayPaymentFactory;
    AlipayAPIFactory alipayAPIFactory;

    @BeforeEach
    public void setUp() {
        alipayPaymentFactory = new AlipayPaymentFactory();
        alipayAPIFactory = new AlipayAPIFactory();
    }

    @Test
    public void testAlipayPaymentFactory() {
        Payment payment = alipayPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof AlipayPayment);
    }

    @Test
    public void testAlipayPaymentFactory_ExternalAPI() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI();
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertTrue(payment instanceof AlipayPayment);
    }

    @Test
    public void testAlipayPaymentFactory_ExternalAPI_Null() {
        Payment payment = alipayPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertTrue(payment instanceof AlipayPayment);
    }

    @Test
    public void testDoPayment() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI(new Random(10));
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(PaymentStatus.SUCCESS, payment.getPaymentStatus());
    }

    @Test
    public void testDoPayment_False() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI(new Random(10));
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(PaymentStatus.FAIL, payment.getPaymentStatus());
    }
    
    @Test
    public void testGetPaymentStatus() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI(new Random(10));
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertEquals(PaymentStatus.NOT_PROCEED, payment.getPaymentStatus());
    }
    
    @Test
    public void testGetPaymentType(){
        Payment alipayPayment = alipayPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentType.ALIPAY, alipayPayment.getPaymentType());
    }
}
