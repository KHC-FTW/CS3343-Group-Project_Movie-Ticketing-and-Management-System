package ExternalAPI;

import java.util.Random;

/**
 * PayMeAPIFactory class<br>
 * It is used to create the PayMeAPI object
 */
public class PayMeAPIFactory implements ExternalAPIFactory {
    /**
     * Create ExternalAPI object
     *
     * @return PayMeAPI object for the PayMe API
     */
    @Override
    public ExternalAPI getExternalAPI() {
        return new PayMeAPI();
    }

    /**
     * Create ExternalAPI object with random object
     *
     * @param random Random object to be used for testing
     * @return PayMeAPI object for the PayMe API with random object if random is not null, otherwise return PayMeAPI object
     */
    @Override
    public ExternalAPI getExternalAPI(Random random) {
        if (random == null) {
            return new PayMeAPI();
        }
        return new PayMeAPI(random);
    }
}
