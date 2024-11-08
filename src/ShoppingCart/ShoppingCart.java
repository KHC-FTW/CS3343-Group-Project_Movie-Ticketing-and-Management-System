package ShoppingCart;

import Product.Product;
import Product.MovieTicket;
import selectMovieModule.Movie;


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

    public Optional<MovieTicket> searchMovieTicket(MovieTicket movieTicket) {
        for (MovieTicket movieTicketInCart : movieTicketCart) {
            if (movieTicket.equals(movieTicketInCart)) {
                return Optional.of(movieTicket);
            }
        }
        return Optional.empty();
    }

    /**
     * Add a movieTicket to the shopping cart
     * It checks if the movie ticket is already in the shopping cart, if it is, it throws an InvalidMovieTicketException,
     * as there should have no duplicate movie tickets in the shopping cart
     *
     * @param movieTicket : movieTicket to add to the shopping cart
     * @throws InvalidMovieTicketException if the movie ticket is already in the shopping cart
     */
    public void addMovieTicket(MovieTicket movieTicket) throws InvalidMovieTicketException {
        Optional<MovieTicket> movieTicketInCart = searchMovieTicket(movieTicket);
        if (movieTicketInCart.isPresent()) {
            throw new InvalidMovieTicketException();
        }
        movieTicketCart.add(movieTicket);
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
     * Remove a product from the shopping cart by decreasing the quantity by the number passed into the method
     * If the quantity of the product is smaller than or equal to 0 after the quantity is decreased, remove the product from the shopping cart<br>
     *
     * @param objectInCart the type of the product, either Product or Movie
     * @param quantity     the quantity of the product to remove
     * @return empty if the product is not in the shopping cart, otherwise return the quantity of the product before removing
     */
    public Optional<Integer> removeFromProductCart(Product objectInCart, int quantity) {
        Integer CartCount = productCart.get(objectInCart);
        if (CartCount == null) {
            return Optional.empty();
        }
        int newCartCount = CartCount - quantity;
        if (newCartCount <= 0) {
            productCart.remove(objectInCart);
        } else {
            productCart.put(objectInCart, newCartCount);
        }
        return Optional.of(CartCount);
    }

    /**
     * Remove a movie ticket from the shopping cart
     *
     * @param movieTicket : movie ticket to remove from the shopping cart
     * @return the movie ticket removed from the shopping cart if it is in the shopping cart, empty otherwise
     */
    public Optional<MovieTicket> removeMovieTicket(MovieTicket movieTicket) {
        Optional<MovieTicket> movieTicketInCart = searchMovieTicket(movieTicket);
        if (movieTicketInCart.isEmpty()) {
            return Optional.empty();
        }
        movieTicketCart.remove(movieTicket);
        return movieTicketInCart;
    }
}