package release.externalAPI;

import java.util.Random;

/**
 * AlipayAPIFactory class<br>
 * It is used to create the AlipayAPI object
 */
public class AlipayAPIFactory implements ExternalAPIFactory {
    /**
     * Create a AlipayAPI object
     *
     * @return AlipayAPI object
     */
    @Override
    public ExternalAPI getExternalAPI() {
        return new AlipayAPI();
    }

    /**
     * Create a AlipayAPI object with a random object
     *
     * @param random Random object to be used for testing
     * @return AlipayAPI object with random object if random is not null, otherwise return AlipayAPI object
     */
    @Override
    public ExternalAPI getExternalAPI(Random random) {
        if (random == null) {
            return new AlipayAPI();
        }
        return new AlipayAPI(random);
    }
}
