package Payment;

import ExternalAPI.ExternalAPI;

public class PayMePaymentFactory implements PaymentFactory {
    /**
     * @return Payment object of PayMePayment
     */
    @Override
    public Payment createPaymentMethod() {
        return new PayMePayment();
    }

    /**
     * @param externalAPI ExternalAPI object to be used for payment 
     * @return Payment object of PayMePayment
     */
    @Override
    public Payment createPaymentMethod(ExternalAPI externalAPI) {
        if (externalAPI == null) {
            return new PayMePayment();
        }
        return new PayMePayment(externalAPI);
    }
}
