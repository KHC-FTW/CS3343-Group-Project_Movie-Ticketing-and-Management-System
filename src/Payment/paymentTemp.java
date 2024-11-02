package Payment;

import java.util.Random;

public class paymentTemp {
    Random random;
//    String cardNumber;
    public paymentTemp(Random random) {
        this.random = random;
    }
    public boolean doPayment(int price){
        // call external API
        // boolean paymentStatus = callExternalAPI(price);
        return random.nextInt() > price/* place in ExternalAPI */;
    }
    
}
// main : xxx = new payment(new random)
// factory : xxx = paymentFactory().dopayment()

// option 
/*
* 1. payme
* paymeObject().doPayment()
* */