package Payment;

import ExternalAPI.ExternalAPI;
import ExternalAPI.WeChatPayAPIFactory;


public class WeChatPayPayment implements Payment{
    PaymentStatus paymentStatus;
    ExternalAPI weChatPayAPI;
    
    WeChatPayPayment(){
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.weChatPayAPI = new WeChatPayAPIFactory().getExternalAPI();
    }
    
    WeChatPayPayment(ExternalAPI weChatPayAPI){
        this.paymentStatus = PaymentStatus.NOT_PROCEED;
        this.weChatPayAPI = weChatPayAPI;
    }
    /**
     * @param price price of the product 
     * @return
     */
    @Override
    public boolean doPayment(double price) {
        System.out.println("WeChatPay Payment: $" + price);
        boolean result = weChatPayAPI.doPayment(price);
        paymentStatus = result ? PaymentStatus.SUCCESS : PaymentStatus.FAIL;
        return result;
    }

    /**
     * @return 
     */
    @Override
    public PaymentType getPaymentType() {
        return PaymentType.WECHATPAY;
    }

    /**
     * @return 
     */
    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
