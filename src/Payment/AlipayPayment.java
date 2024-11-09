package Payment;

import ExternalAPI.AlipayAPIFactory;
import ExternalAPI.ExternalAPI;

/**
 * AlipayPayment class<br>
 * It is used to do payment with Alipay
 */
public class AlipayPayment implements Payment {
    private final ExternalAPI alipayAPI;
    private PaymentStatus paymentStatus;

    /**
     * Constructor of AlipayPayment
     */
    AlipayPayment() {
        paymentStatus = PaymentStatus.NOT_PROCEED;
        alipayAPI = new AlipayAPIFactory().getExternalAPI();
    }

    /**
     * Constructor of AlipayPayment
     *
     * @param alipayAPI AlipayAPI object to be used for testing
     */
    AlipayPayment(ExternalAPI alipayAPI) {
        this.alipayAPI = alipayAPI;
        paymentStatus = PaymentStatus.NOT_PROCEED;
    }

    /**
     * Do payment with Octopus card by simulating the payment process using Octopus card API
     *
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        System.out.println("Alipay Payment: $" + price);
        boolean result = alipayAPI.doPayment(price);
        paymentStatus = result ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
        return result;
    }

    /**
     * Get the payment type
     *
     * @return PaymentType.ALIPAY for Alipay
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.ALIPAY;
    }

    /**
     * Get the payment status
     *
     * @return PaymentStatus of the payment method
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
