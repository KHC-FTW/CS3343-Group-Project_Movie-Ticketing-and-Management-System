package Payment;

/**
 * Payment interface
 * It is used to simulate the payment process of the payment method
 */
public interface Payment {
    /**
     * Do payment with the payment method which implements this interface
     *
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    boolean doPayment(double price);

    /**
     * Get the payment type
     *
     * @return PaymentType of the payment method
     */
    PaymentType getPaymentType();
}
