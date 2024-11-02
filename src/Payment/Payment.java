package Payment;

public interface Payment {
    boolean doPayment(int price);
    PaymentType getPaymentType();
}
