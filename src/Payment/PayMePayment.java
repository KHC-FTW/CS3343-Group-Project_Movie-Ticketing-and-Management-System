package Payment;

import ExternalAPI.ExternalAPI;
import ExternalAPI.PayMeAPIFactory;

/**
 * PayMePayment class<br>
 * It is used to do payment with PayMeAPI
 */
public class PayMePayment implements Payment {
    PaymentStatus paymentStatus;
    final ExternalAPI payMeAPI;

    /**
     * Constructor of PayMePayment
     */
    PayMePayment() {
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.payMeAPI = new PayMeAPIFactory().getExternalAPI();
    }

    /**
     * Constructor of PayMePayment with ExternalAPI object for testing
     *
     * @param payMeAPI ExternalAPI object to be used for payment
     */
    PayMePayment(ExternalAPI payMeAPI) {
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.payMeAPI = payMeAPI;
    }

    /**
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        System.out.println("PayMe Payment: $" + price);
        boolean result = payMeAPI.doPayment(price);
        paymentStatus = result ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
        return result;
    }

    /**
     * Get the payment type
     *
     * @return PaymentType.PAYME for PayMe
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.PAYME;
    }

    /**
     * Get the payment status
     *
     * @return PaymentStatus of the payment method
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
