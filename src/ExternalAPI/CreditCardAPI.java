package ExternalAPI;

import java.util.Random;

/**
 * CreditCardAPI class<br>
 * It is used to simulate the payment process of the Credit Card API
 */
public class CreditCardAPI implements ExternalAPI {
    Random random;

    /**
     * Default constructor
     */
    CreditCardAPI() {
        random = new Random();
    }

    /**
     * Constructor with random object for testing with specified random seed
     *
     * @param random Random object to be used for testing
     */
    CreditCardAPI(Random random) {
        this.random = random;
    }

    /**
     * Simulate the payment process of the credit card API
     *
     * @param price price of the product in shopping cart
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean doPayment(double price) {
        return random.nextInt(0, (int) price * 2) >= price;
    }
}
