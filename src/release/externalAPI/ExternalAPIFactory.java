package release.externalAPI;

import java.util.Random;

/**
 * ExternalAPIFactory interface<br>
 * It is used to create the ExternalAPI object
 */
public interface ExternalAPIFactory {
    /**
     * Create ExternalAPI object
     *
     * @return ExternalAPI object
     */
    ExternalAPI getExternalAPI();

    /**
     * Create ExternalAPI object with random object
     *
     * @param random Random object to be used for testing
     * @return ExternalAPI object
     */
    ExternalAPI getExternalAPI(Random random);
}
