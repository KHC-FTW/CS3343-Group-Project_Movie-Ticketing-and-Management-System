package release.command;

import release.user.Member;
import release.user.UserCenter;

import java.util.Scanner;

public class CmdListShoppingCart implements Command {
    private final UserCenter uc = UserCenter.getInstance();
    private final Member currentUser = (Member) uc.getCurrentUser();
    @Override
    public void execute(Scanner scanner) {
        System.out.println("Please find your selected items as shown below;");
        System.out.println(currentUser.getShoppingCart().formattedToString());
        double totalPrice = currentUser.getShoppingCart().getTotalPrice();
        double discount = currentUser.getCategory().getDiscount();
        double discountedPrice = totalPrice * discount;
        if (Double.compare(discount, 1.0) == 0){
            System.out.printf("Total price: $%.1f%n%n", totalPrice);
        }else{
            System.out.printf("Total price (discounted): $%.1f x %.1f = $%.1f%n%n", totalPrice, discount, discountedPrice);
        }
    }
}

