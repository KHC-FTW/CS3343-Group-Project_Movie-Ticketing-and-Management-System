package Payment;

import ExternalAPI.OctopusAPIFactory;

/**
 * OctopusPayment class
 * It is used to do payment with Octopus card
 */
public class OctopusPayment implements Payment {
    OctopusAPIFactory octopusAPIFactory;
    /**
     * Constructor
     * Create OctopusAPIFactory object for Octopus card API to simulate the payment process
     */
    public OctopusPayment(){
        octopusAPIFactory = new OctopusAPIFactory();
    }
    
    /**
     * Do payment with Octopus card by simulating the payment process using Octopus card API
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(int price) {
        System.out.println("Octopus Payment: $" + price);
        return octopusAPIFactory.getExternalAPI().doPayment(price);
    }

    /**
     * Get the payment type
     * @return PaymentType.OCTOPUS for Octopus card
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.OCTOPUS;
    }
}
