package ExternalAPI;

import java.util.Random;

public class WeChatPayAPI implements ExternalAPI {
    Random random;

    /**
     * Default constructor
     */
    WeChatPayAPI() {
        random = new Random();
    }

    /**
     * Constructor with random object
     *
     * @param random Random object to be used for testing
     */

    WeChatPayAPI(Random random) {
        this.random = random;
    }

    /**
     * Simulate the payment process of the WeChatPay API using random to generate a random number to determine if payment is successful
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        return random.nextInt(0, (int) price * 2) >= price;
    }
}
