package Payment;

import ExternalAPI.ExternalAPI;
import ExternalAPI.PayMeAPIFactory;

public class PayMePayment implements Payment{
    PaymentStatus paymentStatus;
    ExternalAPI payMeAPI;
    
    PayMePayment(){
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.payMeAPI = new PayMeAPIFactory().getExternalAPI();
    }
   
    PayMePayment(ExternalAPI payMeAPI){
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.payMeAPI = payMeAPI;
    }
    
    /**
     * 
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
     * @return 
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.PAYME;
    }

    /**
     * @return 
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
