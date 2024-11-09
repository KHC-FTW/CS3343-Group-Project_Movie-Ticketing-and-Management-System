package TestExternalAPI;

import ExternalAPI.ExternalAPI;
import ExternalAPI.PayMeAPI;
import ExternalAPI.PayMeAPIFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class PayMeAPITest {
    Random random;
    PayMeAPIFactory payMeAPIFactory;
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        payMeAPIFactory = new PayMeAPIFactory();
    }
    
    @Test
    public void testGetExternalAPI() {
        ExternalAPI externalAPI =  payMeAPIFactory.getExternalAPI();
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof PayMeAPI);
    }
    
    @Test
    public void testGetExternalAPI_Random() {
        ExternalAPI externalAPI =  payMeAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof PayMeAPI);
    }
    
    @Test
    public void testGetExternalAPI_Null_Random() {
        ExternalAPI externalAPI =  payMeAPIFactory.getExternalAPI(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> externalAPI.doPayment(1));
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof PayMeAPI);
    }
    
    @Test
    public void testDoPayment() {
        ExternalAPI externalAPI =  payMeAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(externalAPI.doPayment(100));
    }
    
    @Test
    public void testDoPayment_False() {
        ExternalAPI externalAPI =  payMeAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(externalAPI.doPayment(15));
    }
}
