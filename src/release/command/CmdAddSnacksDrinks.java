package release.command;

import release.database.Database;
import release.exception.ExInsufficientArgs;
import release.exception.ExInvalidInputLength;
import release.exception.ExInvalidOption;
import release.exception.ExInvalidPrice;
import release.helper.FormatChecker;
import release.product.Drink;
import release.product.ProductWithPortion;
import release.product.Snack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CmdAddSnacksDrinks implements Command{
    private final List<ProductWithPortion> addedProducts = new ArrayList<>();
    private final Database db = Database.getInstance();
    private final FormatChecker fc = FormatChecker.getInstance();
    private String input = "";

    @Override
    public void execute(Scanner scanner) throws Exception {
        System.out.println("[Remind] Leaving the process at any time by entering \"quit\" or \"q\".\n");
        boolean isSnacks = true;
        while(true) {
            try {
                System.out.print("Product type: 1) Snack or 2) Drink\n> ");
                input = scanner.nextLine().strip();
                System.out.println();
                if (input.equals("q") || input.equals("quit"))
                    break;
                isSnacks = switch (input) {
                    case "1", "Snack" -> true;
                    case "2", "Drink" -> false;
                    default -> throw new ExInvalidOption("[Exception] Invalid option, please try again.");
                };
                String type = isSnacks ? "snack" : "drink";
                System.out.print("Enter the snack/drink data in this format:\n[name], [portion], [price]\n> ");
                input = scanner.nextLine().strip();
                System.out.println();

                if (input.equals("q") || input.equals("quit"))
                    break;

                String[] args = Arrays.stream(input.split(","))
                        .map(String::strip)
                        .toArray(String[]::new);

                if(isValidProductData(args)){
                    String name = args[0];
                    String portion = args[1];
                    double price = Double.parseDouble(args[2]);
                    ProductWithPortion product = null;
                    if(isSnacks){
                        product = new Snack(name, price, portion);
                    }else{
                        product = new Drink(name, price, portion);
                    }
                    if(isDuplicate(addedProducts, product) || isDuplicate(db.getProducts(), product)){
                        System.out.println("[Exception] Duplicate product detected. Failed to add. Please try again.\n");
                        continue;
                    }
                    db.addProduct(product);
                    addedProducts.add(product);
                    System.out.printf("[State] Successfully added %s, %s!%n%n", type, name);
                }

            } catch (ExInvalidOption e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
        if (addedProducts.isEmpty())
            System.out.println("[State] Add snacks/drinks process terminated!\n");
        else {
            int productCnt = 0;
            StringBuilder results = new StringBuilder("All newly added snacks/drinks:\n")
                    .append(String.format("%4s%-32s", " ", "Product Name"))
                    .append(String.format("%-16s", "Portion"))
                    .append(String.format("%-6s", "Price"))
                    .append("\n").append("-".repeat(58)).append("\n");
            for (ProductWithPortion product: addedProducts) {
                results.append(String.format("%2d) ", ++productCnt))
                        .append(String.format("%-30s%2s", product.getName(), " "))
                        .append(String.format("%-16s", ((ProductWithPortion) product).getPortion()))
                        .append(String.format("$%-4.1f", product.getPrice())).append("\n");
            }
            System.out.println(results.toString());
        }
    }

    private boolean isDuplicate(List<ProductWithPortion> addedProducts, ProductWithPortion product) {
        for (ProductWithPortion item: addedProducts) {
            if (item.equals(product))
                return true;
        }
        return false;
    }

    private boolean isValidProductData(String[] args) throws ExInsufficientArgs {
        if (args.length != 3)
            throw new ExInsufficientArgs("[Exception] 3 values must be provided.");
        boolean isValidData = true;
        try{
            fc.checkInputDynamic(args[0], "product name", 30);
        }catch(ExInvalidInputLength e){
            isValidData = false;
            System.out.println(e.getMessage() + "\n");
        }
        try {
            fc.checkInputDynamic(args[1], "portion", 14);
        } catch (ExInvalidInputLength e) {
            isValidData = false;
            System.out.println(e.getMessage() + "\n");
        }
        try {
            fc.checkPrice(args[2]);
        } catch (ExInvalidPrice e) {
            isValidData = false;
            System.out.println("[Exception] Snacks/drinks price must be between 1 and 500.\n");
        }
        return isValidData;
    }
}
