package ExternalAPI;

import java.util.Random;

/**
 * OctopusAPIFactory class
 * It is used to create the OctopusAPI object
 */
public class OctopusAPIFactory implements ExternalAPIFactory {
    /**
     * Create ExternalAPI object
     * @return OctopusAPI object for the octopus card API
     */
    @Override
    public ExternalAPI createExternalAPI() {
        return new OctopusAPI();
    }

    /**
     * Create ExternalAPI object with random object
     * @param random Random object to be used for testing
     * @return OctopusAPI object for the octopus card API
     */
    @Override
    public ExternalAPI createExternalAPI(Random random) {
        return new OctopusAPI(random);
    }
}
