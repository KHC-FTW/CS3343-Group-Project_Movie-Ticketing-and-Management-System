package release.externalAPI;

import java.util.Random;

/**
 * OctopusAPIFactory class<br>
 * It is used to create the OctopusAPI object
 */
public class OctopusAPIFactory implements ExternalAPIFactory {
    /**
     * Create ExternalAPI object
     *
     * @return OctopusAPI object for the octopus card API
     */
    @Override
    public ExternalAPI getExternalAPI() {
        return new OctopusAPI();
    }

    /**
     * Create ExternalAPI object with random object
     *
     * @param random Random object to be used for testing
     * @return OctopusAPI object for the octopus card API with random object if random is not null, otherwise return OctopusAPI object
     */
    @Override
    public ExternalAPI getExternalAPI(Random random) {
        if (random == null) {
            return new OctopusAPI();
        }
        return new OctopusAPI(random);
    }
}
