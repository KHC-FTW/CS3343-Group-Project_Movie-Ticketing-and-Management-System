package release.command;

import release.exception.ExEarlyQuit;
import release.exception.ExInvalidPaymentOption;
import release.movie.BookSeatService;
import release.payment.*;
import release.product.MovieTicket;
import release.product.Product;
import release.record.PaymentRecord;
import release.user.Member;
import release.user.UserCenter;

import java.util.*;

public class CmdDoPayment implements Command{
    private final BookSeatService bSS = BookSeatService.getInstance();
    private final UserCenter uc = UserCenter.getInstance();

    private final List<Payment> payments = new ArrayList<>(Arrays.asList(
            new AlipayPaymentFactory().createPaymentMethod(),
            new CreditCardPaymentFactory().createPaymentMethod(),
            new OctopusPaymentFactory().createPaymentMethod(),
            new PayMePaymentFactory().createPaymentMethod(),
            new WeChatPayPaymentFactory().createPaymentMethod())
    );

    private final Member currentUser = (Member) uc.getCurrentUser();
//    private Scanner scanner;

    @Override
    public void execute(Scanner scanner) throws ExEarlyQuit {
        new CmdListShoppingCart().execute(scanner);
        double discountedPrice = currentUser.getShoppingCart().getTotalPrice() * currentUser.getCategory().getDiscount();
        do{
            if (payments.isEmpty()){
                System.out.println("[Remind] Sorry, you have failed all payment options. Exiting the payment menu.\n");
                // if payment failed, will release all seats and clear the shopping cart
                List<MovieTicket> movieTickes = currentUser.getShoppingCart().getMovieTicketCart();
                for (MovieTicket ticket: movieTickes){
                    bSS.releaseSeat(ticket.getMovieSession().getSeats(), ticket.getSeat());
                }
                currentUser.getShoppingCart().clearAllCart();
                return;
            }
            Map<Integer, Payment> paymentList = new LinkedHashMap<>(payments.size());
            for (int i = 1; i <= payments.size(); i++){
                paymentList.put(i, payments.get(i - 1));
            }

            System.out.print(displayPayment(paymentList));
            String input = scanner.nextLine().strip();
            System.out.println();

            if (input.equals("q") || input.equals("quit")) {
                throw new ExEarlyQuit("payment menu");
            }
            try {
                int option = Integer.parseInt(input);
                Payment selectedPayment = getSelectedPayment(paymentList, option);
                if(selectedPayment.doPayment(discountedPrice)){
                    Map<Product, Integer> paidProducts = currentUser.getShoppingCart().getProductCart();
                    List<MovieTicket> paidTickets = currentUser.getShoppingCart().getMovieTicketCart();
                    PaymentRecord paymentRecord = new PaymentRecord(currentUser, paidProducts, paidTickets, selectedPayment);
                    currentUser.addPaymentRecord(paymentRecord);
                    currentUser.getShoppingCart().clearAllCart();
                    System.out.println("\n[State] Payment successful! Thank you for your purchases at CityU Cinema. Hope to see you again soon!\n");
                    return;
                }else{
                    // payment failure
                    System.out.println("\n[State] Sorry, you don't have enough money to pay with " + selectedPayment.getPaymentType().name() + ".\n");
                    // remove the failed payment from all payments
                    // so user cannot choose it again when asked to pay with other methods
                    payments.remove(selectedPayment);
                }

                /*
                To only test for payment failure.
                Can observe that with each failed payment, the payment option will be removed from the list.
                When all payment options are failed, the system will exit the payment menu.

                System.out.println("[Remind] Sorry, you don't have enough money to pay with " + selectedPayment.getPaymentType().name());
                payments.remove(selectedPayment);
                */

            } catch (ExInvalidPaymentOption e) {
                System.out.println(e.getMessage() + "\n");
            } catch(NumberFormatException e){
                System.out.println(new ExInvalidPaymentOption().getMessage() + "\n");
            }
        }while(true);

    }

    private String displayPayment(Map<Integer, Payment> paymentList){
        StringBuilder results = new StringBuilder("Please select a payment method:\n");
        for (Integer item: paymentList.keySet()){
            results.append(item).append(". ").append(paymentList.get(item).getPaymentType().name()).append("\n");
        }
        return results.append("> ").toString();
    }

    private Payment getSelectedPayment(Map<Integer, Payment> paymentList, int option) throws ExInvalidPaymentOption {
        if (!paymentList.containsKey(option)) throw new ExInvalidPaymentOption();
        return paymentList.get(option);
    }

//    private boolean earlyQuitCheck(String input) {
//        if (input.equalsIgnoreCase("q")) {
//            System.out.print("\n[Warning] If you quit at this stage, any unsaved data will be lost. Confirm? (Y: yes/ Others: No)\n> ");
//            String option = scanner.nextLine();
//            return option.equalsIgnoreCase("Y");
//        }
//        return false;
//    }
}
