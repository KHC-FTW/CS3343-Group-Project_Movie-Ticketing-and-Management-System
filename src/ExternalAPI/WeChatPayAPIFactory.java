package ExternalAPI;

import java.util.Random;

/**
 * WeChatPayAPIFactory class<br>
 * It is used to create the WeChatPayAPI object
 */
public class WeChatPayAPIFactory implements ExternalAPIFactory {
    /**
     * Create ExternalAPI object
     *
     * @return WeChatPayAPI object for the WeChatPay API
     */
    @Override
    public ExternalAPI getExternalAPI() {
        return new WeChatPayAPI();
    }

    /**
     * Create ExternalAPI object with random object
     *
     * @param random Random object to be used for testing
     * @return WeChatPayAPI object for the WeChatPay API with random object if random is not null, otherwise return WeChatPayAPI object
     */
    @Override
    public ExternalAPI getExternalAPI(Random random) {
        if (random == null) {
            return new WeChatPayAPI();
        }
        return new WeChatPayAPI(random);
    }
}
