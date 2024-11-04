package Payment;

public class OctopusPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPaymentMethod() {
        return new OctopusPayment();
    }
    
}
