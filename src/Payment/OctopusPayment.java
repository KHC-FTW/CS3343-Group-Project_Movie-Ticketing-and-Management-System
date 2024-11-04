package Payment;

public class OctopusPayment implements Payment {
    @Override
    public boolean doPayment(int price) {
        System.out.println("Octopus Payment: $" + price);
        //TODO: call external API
        return true;
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.OCTOPUS;
    }
}
