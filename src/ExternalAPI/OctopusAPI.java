package ExternalAPI;

import java.util.Random;

/**
 * OctopusAPI class
 * It is used to simulate the payment process of the octopus card API
 */
public class OctopusAPI implements ExternalAPI {

    private Random random;

    /**
     * Default constructor
     */
    OctopusAPI() {
        random = new Random();
    }

    /**
     * Constructor with random object
     *
     * @param random Random object to be used for testing
     */
    OctopusAPI(Random random) {
        this.random = random;
    }

    /**
     * Simulate the payment process of the octopus card API
     * The payment is successful if the random number generated is greater than or equal to the price
     *
     * @param price price of the product
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        return random.nextInt(0, (int) price * 2) >= price;
    }
}
