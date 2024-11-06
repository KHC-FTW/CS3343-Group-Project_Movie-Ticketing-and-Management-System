package Payment;

/**
 * PaymentFactory interface
 * It is the factory interface for creating Payment object
 */
public interface PaymentFactory {
    /**
     * Create Payment object
     * @return Payment object
     */
    Payment createPaymentMethod();
}
