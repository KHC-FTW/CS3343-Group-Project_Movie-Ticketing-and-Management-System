package Payment;

import ExternalAPI.ExternalAPI;

/**
 * WeChatPayPaymentFactory class<br>
 * It is used to create WeChatPayPayment object
 */
public class WeChatPayPaymentFactory implements PaymentFactory {

    /**
     * Create a WeChatPayPayment object
     *
     * @return Payment object of WeChatPayPayment
     */
    @Override
    public Payment createPaymentMethod() {
        return new WeChatPayPayment();
    }

    /**
     * Create a WeChatPayPayment object with ExternalAPI object for testing
     *
     * @param externalAPI ExternalAPI object to be used for payment
     * @return Payment object of WeChatPayPayment with ExternalAPI object if externalAPI is not null, otherwise return WeChatPayPayment object
     */
    @Override
    public Payment createPaymentMethod(ExternalAPI externalAPI) {
        if (externalAPI == null) {
            return new WeChatPayPayment();
        }
        return new WeChatPayPayment(externalAPI);
    }
}
