package release.externalAPI;

import java.util.Random;

/**
 * WeChatPayAPI class<br>
 * It is used to simulate the payment process of the WeChatPay API
 */
public class WeChatPayAPI implements ExternalAPI {
    final Random random;

    /**
     * Default constructor
     */
    WeChatPayAPI() {
        random = new Random();
    }

    /**
     * Constructor with random object for testing with specified random seed
     *
     * @param random Random object to be used for testing
     */

    WeChatPayAPI(Random random) {
        this.random = random;
    }

    /**
     * Simulate the payment process of the WeChatPay API using random to generate a random number to determine if payment is successful
     *
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        return random.nextInt(0, (int) price * 2) >= price;
    }
}
