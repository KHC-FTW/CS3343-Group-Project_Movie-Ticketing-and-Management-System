package Payment;

import ExternalAPI.ExternalAPI;
import ExternalAPI.CreditCardAPIFactory;

public class CreditCardPayment implements Payment {
    PaymentStatus paymentStatus;
    private final ExternalAPI externalAPI;
    
    /**
     * Constructor for CreditCardPayment
     */
    public CreditCardPayment() {
        this.externalAPI = new CreditCardAPIFactory().getExternalAPI();
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
    }
    
    /**
     * Constructor for CreditCardPayment
     * @param externalAPI ExternalAPI object to be used for testing
     */
    public CreditCardPayment(ExternalAPI externalAPI) {
        this.externalAPI = externalAPI;
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
    }
    /**
     * Do payment with credit card by simulating the payment process using credit card API
     * @param price price of the product 
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        System.out.println("Credit Card Payment: $" + price);
        boolean result = externalAPI.doPayment(price);
        paymentStatus = result ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
        return result;
    }

    /**
     * Get the payment type
     * @return PaymentType.CREDIT_CARD for Credit Card
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CREDIT_CARD;
    }

    /**
     * Get the payment status
     * @return PaymentStatus of the payment method
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
