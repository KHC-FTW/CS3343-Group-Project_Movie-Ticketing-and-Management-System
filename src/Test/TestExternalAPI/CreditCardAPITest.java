package TestExternalAPI;

import ExternalAPI.CreditCardAPI;
import ExternalAPI.CreditCardAPIFactory;
import ExternalAPI.ExternalAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class CreditCardAPITest {
    Random random;
    CreditCardAPIFactory creditCardAPIFactory;
    @BeforeEach
    void setUp() {
        random = new Random(10);
        creditCardAPIFactory = new CreditCardAPIFactory();
    }
    
    @Test
    void testCreditCardAPIFactory() {
         ExternalAPI externalAPI = creditCardAPIFactory.getExternalAPI();
         Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof CreditCardAPI);
    }
    
    @Test
    void testCreditCardAPIFactory_Random() {
        ExternalAPI externalAPI = creditCardAPIFactory.getExternalAPI(random);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof CreditCardAPI);
    }
    
    @Test
    void testCreditCardAPIFactory_Random_Null() {
        ExternalAPI externalAPI = creditCardAPIFactory.getExternalAPI(null);
        // test when random is null, it should return CreditCardAPI object and can do payment without NullPointerException
        externalAPI.doPayment(1);
        Assertions.assertNotNull(externalAPI);
        Assertions.assertTrue(externalAPI instanceof CreditCardAPI);
    }    
    
    @Test
    void testDoPayment() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI(random);
        Assertions.assertTrue(creditCardAPI.doPayment(100));
    }
    
    @Test
    void testDoPayment_Fail() {
        ExternalAPI creditCardAPI = creditCardAPIFactory.getExternalAPI(random);
        Assertions.assertFalse(creditCardAPI.doPayment(15));
    }
}
