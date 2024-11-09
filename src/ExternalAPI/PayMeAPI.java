package ExternalAPI;

import java.util.Random;

/**
 * PaymeAPI class
 * It is used to simulate payment using Payme API
 */
public class PayMeAPI implements ExternalAPI {
    Random random;

    /**
     * Default constructor
     */
    PayMeAPI() {
        random = new Random();
    }

    /**
     * Constructor with random object
     *
     * @param random Random object to be used for testing
     */
    PayMeAPI(Random random) {
        this.random = random;
    }

    /**
     * Simulate the payment process of the Payme API using random to generate a random number to determine if payment is successful
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        return random.nextInt(0, (int) price * 2) >= price;
    }
}
