package Payment;

import ExternalAPI.ExternalAPI;

public class CreditCardPaymentFactory implements PaymentFactory {

    /**
     * Create a CreditCardPayment object
     * @return Payment object of CreditCardPayment
     */
    @Override
    public Payment createPaymentMethod() {
        return new CreditCardPayment();
    }

    /**
     * Create a CreditCardPayment object with ExternalAPI object for testing
     * @param externalAPI ExternalAPI object to be used for payment 
     * @return Payment object of CreditCardPayment
     */
    @Override
    public Payment createPaymentMethod(ExternalAPI externalAPI) {
        if (externalAPI == null) {
            return new CreditCardPayment();
        }
        return new CreditCardPayment(externalAPI);
    }
}
