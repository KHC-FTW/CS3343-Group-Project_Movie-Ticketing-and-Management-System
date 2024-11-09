package TestExternalAPI;

import ExternalAPI.ExternalAPI;
import ExternalAPI.WeChatPayAPI;
import ExternalAPI.WeChatPayAPIFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class WeChatPayAPITest {
    Random random;
    WeChatPayAPIFactory weChatPayAPIFactory;
    
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        weChatPayAPIFactory = new WeChatPayAPIFactory();
    }
    
    @Test
    public void testGetExternalAPI() {
        ExternalAPI externalAPI =  weChatPayAPIFactory.getExternalAPI();
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof WeChatPayAPI);
    }
    
    @Test
    public void testGetExternalAPI_Random() {
        ExternalAPI externalAPI =  weChatPayAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof WeChatPayAPI);
    }
    
    @Test
    public void testGetExternalAPI_Null_Random() {
        ExternalAPI externalAPI =  weChatPayAPIFactory.getExternalAPI(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> externalAPI.doPayment(1));
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof WeChatPayAPI);
    }
    
    @Test
    public void testDoPayment() {
        ExternalAPI externalAPI =  weChatPayAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(externalAPI.doPayment(100));
    }
    
    @Test
    public void testDoPayment_False() {
        ExternalAPI externalAPI =  weChatPayAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(externalAPI.doPayment(15));
    }
    
}
