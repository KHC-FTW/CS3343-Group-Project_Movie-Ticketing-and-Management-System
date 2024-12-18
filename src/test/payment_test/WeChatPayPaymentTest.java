package test.payment_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.externalAPI.WeChatPayAPIFactory;
import release.payment.*;

import java.util.Random;

/**
 * WeChatPayPaymentTest class<br>
 * It is used to test the WeChatPayPayment class and WeChatPayPaymentFactory class
 */
public class WeChatPayPaymentTest {
    WeChatPayPaymentFactory weChatPayPaymentFactory;
    WeChatPayAPIFactory weChatPayAPIFactory;

    /**
     * Set up the test environment
     */
    @BeforeEach
    public void setUp() {
        weChatPayPaymentFactory = new WeChatPayPaymentFactory();
        weChatPayAPIFactory = new WeChatPayAPIFactory();
    }

    /**
     * Test if the WeChatPayPaymentFactory can create an instance of WeChatPayPayment
     */
    @Test
    public void testWeChatPayPaymentFactory() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod();
        Assertions.assertInstanceOf(WeChatPayPayment.class, payment);
    }

    /**
     * Test if the WeChatPayPaymentFactory can create an instance of WeChatPayPayment with an ExternalAPI object
     */
    @Test
    public void testWeChatPayPaymentFactory_ExternalAPI() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(weChatPayAPIFactory.getExternalAPI());
        Assertions.assertInstanceOf(WeChatPayPayment.class, payment);
    }

    /**
     * Test if the WeChatPayPaymentFactory can create an instance of WeChatPayPayment with a null ExternalAPI object<br>
     * The method should not throw NullPointerException
     */
    @Test
    public void testWeChatPayPaymentFactory_ExternalAPI_Null() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertInstanceOf(WeChatPayPayment.class, payment);
    }

    /**
     * Test if the WeChatPayPayment can do payment successfully<br>
     * The payment should be successful, and the payment status should be SUCCESS
     */
    @Test
    public void testDoPayment() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(
                weChatPayAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(PaymentStatus.SUCCESS, payment.getPaymentStatus());
    }

    /**
     * Test if the WeChatPayPayment can do payment unsuccessfully<br>
     * The payment should fail, as price of 15 should be greater than the random number generated<br>
     * The payment status should be FAIL
     */
    @Test
    public void testDoPayment_False() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod(
                weChatPayAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(PaymentStatus.FAIL, payment.getPaymentStatus());
    }

    /**
     * Test getPaymentType method of WeChatPayPayment class<br>
     * The payment type should be WECHATPAY
     */
    @Test
    public void testGetPaymentType() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentType.WECHATPAY, payment.getPaymentType());
    }

    /**
     * Test getPaymentStatus method of WeChatPayPayment class<br>
     * The payment status should be NOT_PROCEED
     */
    @Test
    public void testGetPaymentStatus() {
        Payment payment = weChatPayPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentStatus.NOT_PROCEED, payment.getPaymentStatus());
    }
}
