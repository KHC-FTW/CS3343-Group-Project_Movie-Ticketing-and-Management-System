package TestPayment;

import Payment.OctopusPaymentFactory;
import Payment.Payment;
import Payment.OctopusPayment;
import Payment.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OctopusPaymentTest {
    @Test
    public void testOctopusPaymentFactory() {
        OctopusPaymentFactory octopusPaymentFactory = new OctopusPaymentFactory();
        Payment payment =  octopusPaymentFactory.createPaymentMethod();
        Assertions.assertTrue(payment instanceof OctopusPayment);
    }
    
    @Test
    public void testOctopusGetPaymentType() {
        OctopusPayment octopusPayment = new OctopusPayment();
        Assertions.assertEquals(PaymentType.OCTOPUS, octopusPayment.getPaymentType());
    }
    
    @Test
    public void testOctopusDoPayment() {
        class OctopusPaymentStub extends OctopusPayment {
            @Override
            public boolean doPayment(int price) {
                return true;
            }
        }
        OctopusPayment octopusPayment = new OctopusPaymentStub();
        Assertions.assertTrue(octopusPayment.doPayment(100));
    }
}
