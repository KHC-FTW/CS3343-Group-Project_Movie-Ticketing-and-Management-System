package Payment;

import ExternalAPI.OctopusAPIFactory;

/**
 * OctopusPayment class
 * It is used to do payment with Octopus card
 */
public class OctopusPayment implements Payment {
    private final OctopusAPIFactory octopusAPIFactory;
    private PaymentStatus paymentStatus;

    /**
     * Constructor
     * Create OctopusAPIFactory object for Octopus card API to simulate the payment process
     */
    public OctopusPayment() {
        octopusAPIFactory = new OctopusAPIFactory();
        paymentStatus = PaymentStatus.NOT_PROCEED;
    }

    /**
     * Do payment with Octopus card by simulating the payment process using Octopus card API
     *
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        System.out.println("Octopus Payment: $" + price);
        boolean result = octopusAPIFactory.getExternalAPI().doPayment(price);
        paymentStatus = result ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
        return result;
    }

    /**
     * Get the payment type
     *
     * @return PaymentType.OCTOPUS for Octopus card
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.OCTOPUS;
    }

    /**
     * get the payment status of this payment
     *
     * @return PaymentStatus.NOT_PROCEED if the payment have not done, else return the result of payment (PaymentStatus.SUCCESS if success, otherwise return PaymentStatus.FAIL.)
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
