package release.payment;

import release.externalAPI.*;

/**
 * WeChatPayPayment class that implements Payment interface
 */
public class WeChatPayPayment implements Payment {
    PaymentStatus paymentStatus;
    final ExternalAPI weChatPayAPI;

    /**
     * Constructor for WeChatPayPayment
     */
    WeChatPayPayment() {
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.weChatPayAPI = new WeChatPayAPIFactory().getExternalAPI();
    }

    /**
     * Constructor for WeChatPayPayment for testing purpose
     *
     * @param weChatPayAPI WeChatPayAPI object to be used for testing
     */
    WeChatPayPayment(ExternalAPI weChatPayAPI) {
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.weChatPayAPI = weChatPayAPI;
    }

    /**
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        System.out.printf("WeChatPay Payment: $%.1f%n", price);
        boolean result = weChatPayAPI.doPayment(price);
        paymentStatus = result ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
        return result;
    }

    /**
     * get the payment type
     *
     * @return PaymentType.WECHATPAY for WeChatPay
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.WECHATPAY;
    }

    /**
     * get the payment status of this payment
     *
     * @return PaymentStatus of the payment method
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
