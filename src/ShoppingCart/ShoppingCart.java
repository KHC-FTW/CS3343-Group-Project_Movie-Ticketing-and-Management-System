package ShoppingCart;

import Product.MovieTicket;
import Product.Product;

import java.util.*;

/**
 * ShoppingCart class that contains all products in the shopping cart
 */
public class ShoppingCart {
    private final Map<Product, Integer> productCart;
    private final List<MovieTicket> movieTicketCart;

    /**
     * Default constructor for ShoppingCart class, which initializes the shopping Cart of products and movies
     */
    public ShoppingCart() {
        productCart = new HashMap<>();
        movieTicketCart = new ArrayList<>();
    }

    /**
     * Constructor for ShoppingCart class,
     * which the Cart is initialized with the hashmap of products and list of movie ticket passed into the constructor
     *
     * @param productCart     : products in the shopping cart
     * @param movieTicketCart : movies tickets in the shopping cart
     */
    public ShoppingCart(HashMap<Product, Integer> productCart, List<MovieTicket> movieTicketCart) {
        this.productCart = productCart;
        this.movieTicketCart = movieTicketCart;
    }

    /**
     * Add a product to the shopping cart
     *
     * @param product : product to add to the shopping cart
     */
    public void addProduct(Product product) {
        int productCount = productCart.getOrDefault(product, 0);
        productCart.put(product, productCount + 1);
    }

    /**
     * Add a product to the shopping cart with a specific quantity
     *
     * @param product  : product to add to the shopping cart
     * @param quantity : quantity of the product to add to the shopping cart
     */
    public void addProduct(Product product, int quantity) {
        int productCount = productCart.getOrDefault(product, 0);
        productCart.put(product, productCount + quantity);
    }

    /**
     * Get the shopping cart of products
     *
     * @return the products in the shopping cart in Map
     */
    public Map<Product, Integer> getProductCart() {
        return productCart;
    }

    /**
     * Search for a movie ticket in the shopping cart
     *
     * @param movieTicket : movie ticket to search for in the shopping cart
     * @return the movie ticket in the shopping cart if it is in the shopping cart
     * @throws ExProductNotFound if the movie ticket is not in the shopping cart
     */
    public MovieTicket searchMovieTicket(MovieTicket movieTicket) throws ExProductNotFound {
        for (MovieTicket movieTicketInCart : movieTicketCart) {
            if (movieTicket.equals(movieTicketInCart)) {
                return movieTicketInCart;
            }
        }
        throw new ExProductNotFound("[Exception] Movie ticket not found");
    }

    /**
     * Add a movieTicket to the shopping cart<br>
     * It checks if the movie ticket is already in the shopping cart, if it is, it throws an ExInvalidMovieTicket,
     * as there should have no duplicate movie tickets in the shopping cart
     *
     * @param movieTicket : movieTicket to add to the shopping cart
     * @throws ExInvalidMovieTicket if the movie ticket is already in the shopping cart
     */
    public void addMovieTicket(MovieTicket movieTicket) throws ExInvalidMovieTicket {
        try {
            searchMovieTicket(movieTicket);
            throw new ExInvalidMovieTicket();
        } catch (ExProductNotFound e) {
            movieTicketCart.add(movieTicket);
        }
    }

    /**
     * Get the shopping cart of movies
     *
     * @return the movies in the shopping cart in Map
     */
    public List<MovieTicket> getMovieTicketCart() {
        return movieTicketCart;
    }

    /**
     * Clear all shopping cart
     */
    public void clearAllCart() {
        productCart.clear();
        movieTicketCart.clear();
    }

    /**
     * clear the shopping cart of products
     */
    public void clearProductCart() {
        productCart.clear();
    }

    /**
     * clear the shopping cart of movies
     */
    public void clearMovieTicketCart() {
        movieTicketCart.clear();
    }

    /**
     * Remove a product from the shopping cart by decreasing the quantity by the number passed into the method<br>
     * If the quantity of the product is smaller than or equal to 0 after the quantity is decreased, remove the product from the shopping cart<br>
     *
     * @param objectInCart the type of the product, either Product or Movie
     * @param quantity     the quantity of the product to remove
     * @return the quantity of the product before removing
     * @throws ExProductNotFound if the product is not in the shopping cart
     */
    public int removeFromProductCart(Product objectInCart, int quantity) throws ExProductNotFound {
        Integer CartCount = productCart.get(objectInCart);
        if (CartCount == null) {
            throw new ExProductNotFound(
                    String.format("[Exception] Product %s not found", objectInCart.getName()));
        }
        int newCartCount = CartCount - quantity;
        if (newCartCount <= 0) {
            productCart.remove(objectInCart);
        } else {
            productCart.put(objectInCart, newCartCount);
        }
        return CartCount;
    }

    /**
     * Remove a movie ticket from the shopping cart
     *
     * @param movieTicket : movie ticket to remove from the shopping cart
     * @return the movie ticket removed from the shopping cart if it is in the shopping cart
     * @throws ExProductNotFound if the movie ticket is not in the shopping cart
     */
    public MovieTicket removeMovieTicket(MovieTicket movieTicket) throws ExProductNotFound {
        MovieTicket movieTicketInCart = searchMovieTicket(movieTicket);
        movieTicketCart.remove(movieTicket);
        return movieTicketInCart;
    }

    /**
     * Get the total price of the products in the shopping cart
     *
     * @return the total price of the products in the shopping cart
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : productCart.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        for (MovieTicket movieTicket : movieTicketCart) {
            totalPrice += movieTicket.getPrice();
        }
        return totalPrice;
    }


}