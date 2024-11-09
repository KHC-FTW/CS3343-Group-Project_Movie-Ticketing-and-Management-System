package TestPayment;

import ExternalAPI.WeChatPayAPIFactory;
import Payment.WeChatPayPayment;
import Payment.Payment;
import Payment.WeChatPayPaymentFactory;
import Payment.PaymentStatus;
import Payment.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class WeChatPayPaymentTest {
    WeChatPayPaymentFactory weChatPayPaymentFactory;
    WeChatPayAPIFactory weChatPayAPIFactory;

    @BeforeEach
    public void setUp() {
        weChatPayPaymentFactory = new WeChatPayPaymentFactory();
        weChatPayAPIFactory = new WeChatPayAPIFactory();
    }

    @Test
    public void testWeChatPayPaymentFactory() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof WeChatPayPayment);
    }

    @Test
    public void testWeChatPayPaymentFactory_ExternalAPI() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(weChatPayAPIFactory.getExternalAPI());
        Assertions.assertTrue(payment instanceof WeChatPayPayment);
    }

    @Test
    public void testWeChatPayPaymentFactory_ExternalAPI_Null() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertTrue(payment instanceof WeChatPayPayment);
    }

    @Test
    public void testDoPayment() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(
                weChatPayAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.SUCCESS);
    }
    
    @Test
    public void testDoPayment_False() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(
                weChatPayAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.FAIL);
    }
    
    @Test
    public void testGetPaymentType() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentType(), PaymentType.WECHATPAY);
    }
    
    @Test
    public void testGetPaymentStatus() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(payment.getPaymentStatus(), PaymentStatus.NOT_PROCEED);
    }
}
