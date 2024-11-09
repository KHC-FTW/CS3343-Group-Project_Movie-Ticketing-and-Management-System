package ExternalAPI;

import java.util.Random;

/**
 * AlipayAPI class
 * It is used to simulate the payment process of the Alipay API
 */
public class AlipayAPI implements ExternalAPI {
    Random random;

    /**
     * Default Constructor for AlipayAPI
     */
    AlipayAPI() {
        random = new Random();
    }

    /**
     * Constructor for AlipayAPI with random object for testing
     *
     * @param random Random object to be used for testing
     */
    AlipayAPI(Random random) {
        this.random = random;
    }

    /**
     * Simulate the payment process of the Alipay API using random number generator to determine if payment is successful
     * by comparing the random number to the price
     *
     * @param price price of the product in shopping cart
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        return random.nextInt(0, (int) price * 2) >= price;
    }
}
