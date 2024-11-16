package release.externalAPI;

import java.util.Random;

/**
 * CreditCardAPIFactory class<br>
 * It is used to create the CreditCardAPI object
 */
public class CreditCardAPIFactory implements ExternalAPIFactory {
    /**
     * Create a CreditCardAPI object
     *
     * @return CreditCardAPI object
     */
    @Override
    public ExternalAPI getExternalAPI() {
        return new CreditCardAPI();
    }

    /**
     * Create a CreditCardAPI object with a random object
     *
     * @param random Random object to be used for testing
     * @return CreditCardAPI object with random object if random is not null, otherwise return CreditCardAPI object
     */
    @Override
    public ExternalAPI getExternalAPI(Random random) {
        if (random == null) {
            return new CreditCardAPI();
        }
        return new CreditCardAPI(random);
    }
}
