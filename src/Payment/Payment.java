package Payment;

/**
 * Payment interface
 * It is used to simulate the payment process of the payment method
 * * <br><strong>Note: The constructor for the class which implements this interface should be set to package private by not adding any modifier, as they should be created using the corresponding factory method</strong>
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
    
    PaymentStatus getPaymentStatus();
}
