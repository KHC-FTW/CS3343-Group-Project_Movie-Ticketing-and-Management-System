package TestExternalAPI;

import ExternalAPI.AlipayAPIFactory;
import ExternalAPI.ExternalAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AlipayAPITest {
    Random random;
    AlipayAPIFactory alipayAPIFactory;
    
    @BeforeEach
    public void setUp() {
        random = new Random(10);
        alipayAPIFactory = new AlipayAPIFactory();
    }
    
    @Test
    public void testGetExternalAPI() {
        ExternalAPI externalAPI =  alipayAPIFactory.getExternalAPI();
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof ExternalAPI);
    }
    
    @Test
    public void testGetExternalAPI_Random() {
        ExternalAPI externalAPI =  alipayAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof ExternalAPI);
    }
    
    @Test
    public void testGetExternalAPI_Random_Num() {
        ExternalAPI externalAPI =  alipayAPIFactory.getExternalAPI(null);
        // Test if there is no NullPointerException
        externalAPI.doPayment(1);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof ExternalAPI);
    }
    
    @Test
    public void testDoPayment() {
        ExternalAPI externalAPI =  alipayAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(externalAPI.doPayment(100));
    }
    
    @Test
    public void testDoPayment_False() {
        ExternalAPI externalAPI =  alipayAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(externalAPI.doPayment(15));
    }
}
