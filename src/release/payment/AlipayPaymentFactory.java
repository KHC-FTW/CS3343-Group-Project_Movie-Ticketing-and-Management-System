package release.payment;

import release.externalAPI.*;

/**
 * AlipayPaymentFactory class<br>
 * It is used to create the AlipayPayment object
 */
public class AlipayPaymentFactory implements PaymentFactory {


    /**
     * Create a AlipayPayment object
     *
     * @return Payment object of AlipayPayment
     */
    @Override
    public Payment createPaymentMethod() {
        return new AlipayPayment();
    }

    /**
     * Create a AlipayPayment object with ExternalAPI object for testing
     *
     * @param externalAPI ExternalAPI object to be used for payment
     * @return Payment object of AlipayPayment with ExternalAPI object if externalAPI is not null, otherwise return AlipayPayment object
     */
    @Override
    public Payment createPaymentMethod(ExternalAPI externalAPI) {
        if (externalAPI == null) {
            return new AlipayPayment();
        }
        return new AlipayPayment(externalAPI);
    }
}
