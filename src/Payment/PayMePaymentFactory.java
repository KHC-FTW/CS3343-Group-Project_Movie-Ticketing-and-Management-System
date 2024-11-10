package Payment;

import ExternalAPI.ExternalAPI;

/**
 * PayMePaymentFactory class<br>
 * It is used to create PayMePayment object
 */
public class PayMePaymentFactory implements PaymentFactory {
    /**
     * Create a PayMePayment object
     *
     * @return Payment object of PayMePayment
     */
    @Override
    public Payment createPaymentMethod() {
        return new PayMePayment();
    }

    /**
     * Create a PayMePayment object with ExternalAPI object for testing
     *
     * @param externalAPI ExternalAPI object to be used for payment
     * @return Payment object of PayMePayment with ExternalAPI object if externalAPI is not null, otherwise return PayMePayment object
     */
    @Override
    public Payment createPaymentMethod(ExternalAPI externalAPI) {
        if (externalAPI == null) {
            return new PayMePayment();
        }
        return new PayMePayment(externalAPI);
    }
}
