package release.payment;

import release.externalAPI.ExternalAPI;

/**
 * PaymentFactory interface<br>
 * It is the factory interface for creating Payment object
 */
public interface PaymentFactory {
    /**
     * Create Payment object
     *
     * @return Payment object
     */
    Payment createPaymentMethod();

    /**
     * Create Payment object with ExternalAPI object for testing
     *
     * @param externalAPI ExternalAPI object to be used for payment
     * @return Payment object
     */
    Payment createPaymentMethod(ExternalAPI externalAPI);
}
