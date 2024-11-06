package Payment;

/**
 * OctopusPaymentFactory class
 * It is used to create OctopusPayment object
 */
public class OctopusPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPaymentMethod() {
        return new OctopusPayment();
    }
    
}
