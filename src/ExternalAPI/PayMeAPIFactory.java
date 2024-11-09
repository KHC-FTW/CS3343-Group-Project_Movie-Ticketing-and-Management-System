package ExternalAPI;

import java.util.Random;

public class PayMeAPIFactory implements ExternalAPIFactory {
    @Override
    public ExternalAPI getExternalAPI() {
        return new PayMeAPI();
    }

    @Override
    public ExternalAPI getExternalAPI(Random random) {
        if (random == null) {
            return new PayMeAPI();
        }
        return new PayMeAPI(random);
    }
}
