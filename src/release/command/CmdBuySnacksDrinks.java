package release.command;

import release.database.Database;
import release.exception.ExEarlyQuit;
import release.exception.ExInvalidOption;
import release.exception.ExInvalidProductQty;
import release.product.Product;
import release.product.ProductWithPortion;
import release.user.Member;
import release.user.UserCenter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CmdBuySnacksDrinks implements Command{
    private final Database db = Database.getInstance();
    private final UserCenter uc = UserCenter.getInstance();
    private Scanner scanner;
    private final Map<Integer, ProductWithPortion> selectableProducts = new LinkedHashMap<>(db.getProducts().size());
    private final Member currentUser = (Member) uc.getCurrentUser();

    @Override
    public void execute(Scanner scanner) {
        this.scanner = scanner;
        selectSnacksDrinksMenu();
    }

    private void selectSnacksDrinksMenu(){
        if(db.getProducts().isEmpty()){
            System.out.println("[Exception] There are no snacks/drinks for selection.\n");
            return;
        }
        int count = 1;
        for(ProductWithPortion product: db.getProducts()){
            selectableProducts.put(count, product);
            count++;
        }

        String[] requiredInput = new String[]{"Item: ", "Quantity: "};
        int[] inputOptions = new int[2];
        int completedInput = 0;
        System.out.println(displayProducts(selectableProducts));
//        System.out.println("\n[Remind] You may return to the movie booking main menu by entering \"q\" or \"quit\".\n");
        System.out.println("Your snacks/drinks selection:");
        do{
            try{
                System.out.print(requiredInput[completedInput]);
                String input = scanner.nextLine().strip();
                if (input.equals("q") || input.equals("quit")) {
                    System.out.println("\n[State] Leaving snacks/drinks selection menu.\n");
                    return;
                }
                int option = Integer.parseInt(input);

                // getting item input
                if (completedInput == 0){
                    if(!selectableProducts.containsKey(option)){
                        System.out.println("\n" + new ExInvalidOption().getMessage() + "\n");
                    }else{
                        inputOptions[completedInput] = option;
                        completedInput++;
                    }
                    continue;// prepare to get quantity
                }

                if (option <= 0 || option > 10){
                    System.out.println("\n" + new ExInvalidProductQty().getMessage() + "\n");
                }else{
                    inputOptions[completedInput] = option;
                    break;
                }

            }catch (NumberFormatException e){
                System.out.println("\n[Exception] Invalid input format.\n");
            }
        }while(true);

        ProductWithPortion selectedProduct = selectedProduct(selectableProducts, inputOptions[0]);
        int quantity = inputOptions[1];
        currentUser.getShoppingCart().addProduct(selectedProduct, quantity);
        System.out.printf("\n[State] Successfully added %s (%s) x %d to your shopping cart.%n%n", selectedProduct.getName(), selectedProduct.getPortion(), quantity);

    }

    private String displayProducts(Map<Integer, ProductWithPortion> selectableProducts){
        StringBuilder results = new StringBuilder("The following snacks/drinks are available for selection:\n")
                .append(String.format("%-4s", " "))
                .append(String.format("%-32s", "Name"))
                .append(String.format("%-19s", "Portion"))
                .append(String.format("%-7s", "Price"))
                .append("\n").append("-".repeat(62)).append("\n");

        for (Integer item: selectableProducts.keySet()){
            results.append(String.format("%2d) ", item))
                    .append(String.format("%-30s%2s", selectableProducts.get(item).getName(), " "))
                    .append(String.format("%-19s", selectableProducts.get(item).getPortion()))
                    .append(String.format("$%-6.1f", selectableProducts.get(item).getPrice()))
                    .append("\n");
        }
        return results.toString();
    }

    private ProductWithPortion selectedProduct(Map<Integer, ProductWithPortion> selectableProducts, int option){
        return selectableProducts.get(option);
    }
}
