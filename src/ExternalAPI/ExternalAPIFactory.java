package ExternalAPI;

import java.util.Random;

/**
 * ExternalAPIFactory interface
 * It is used to create the ExternalAPI object
 */
public interface ExternalAPIFactory {
    /**
     * Create ExternalAPI object
     * @return ExternalAPI object
     */
    ExternalAPI createExternalAPI();
    /**
     * Create ExternalAPI object with random object
     * @param random Random object to be used for testing
     * @return ExternalAPI object
     */
    ExternalAPI createExternalAPI(Random random);
}
