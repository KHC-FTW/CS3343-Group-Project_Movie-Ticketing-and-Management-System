package release.command;

import release.product.ProductWithPortion;
import release.database.Database;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CmdListSnacksDrinks implements Command{
    private final Database db = Database.getInstance();
    @Override
    public void execute(Scanner scanner){
        List<ProductWithPortion> allProducts = db.getProducts();
        Map<Integer, ProductWithPortion> selectableProducts = new LinkedHashMap<>(allProducts.size());
        if(db.getProducts().isEmpty()){
            System.out.println("[Exception] There are no snacks/drinks to show.\n");
            return;
        }
        int count = 1;
        for(ProductWithPortion product: allProducts){
            selectableProducts.put(count, product);
            count++;
        }
        System.out.println(displayProducts(selectableProducts));
    }

    private String displayProducts(Map<Integer, ProductWithPortion> selectableProducts){
        StringBuilder results = new StringBuilder("List of available snacks/drinks:\n")
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
}
