package ShoppingCart;

import Product.Product;
import selectMovieModule.Movie;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ShoppingCart class that contains all products in the shopping cart
 */
public class ShoppingCart {
    private final Map<Product, Integer> productCart;
    private final Map<Movie, Integer> movieCart;

    /**
     * Default constructor for ShoppingCart class, which initializes the shopping Cart of products and movies
     */
    public ShoppingCart() {
        productCart = new HashMap<>();
        movieCart = new HashMap<>();
    }

    /**
     * Constructor for ShoppingCart class, which the Cart is initialized with the hashmap of products and movies passed into the constructor
     *
     * @param productCart : products in the shopping cart
     * @param movieCart   : movies in the shopping cart
     */
    public ShoppingCart(HashMap<Product, Integer> productCart, HashMap<Movie, Integer> movieCart) {
        this.productCart = productCart;
        this.movieCart = movieCart;
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
     * Add a movie to the shopping cart
     *
     * @param movie : movie to add to the shopping cart
     */
    public void addMovie(Movie movie) {
        int movieCount = movieCart.getOrDefault(movie, 0);
        movieCart.put(movie, movieCount + 1);
    }

    /**
     * Add a movie to the shopping cart with a specific quantity
     *
     * @param movie    : movie to add to the shopping cart
     * @param quantity : quantity of the movie to add to the shopping cart
     */
    public void addMovie(Movie movie, int quantity) {
        int movieCount = movieCart.getOrDefault(movie, 0);
        movieCart.put(movie, movieCount + quantity);
    }

    /**
     * Get the shopping cart of movies
     *
     * @return the movies in the shopping cart in Map
     */
    public Map<Movie, Integer> getMovieCart() {
        return movieCart;
    }

    /**
     * Clear all shopping cart
     */
    public void clearAllCart() {
        productCart.clear();
        movieCart.clear();
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
    public void clearMovieCart() {
        movieCart.clear();
    }
    
    /**
     * Remove a product from the shopping cart by decreasing the quantity by the number passed into the method
     * If the quantity of the product is smaller than or equal to 0 after the quantity is decreased, remove the product from the shopping cart<br>
     * <strong>Note: If movie and product are modified so that they have the same parent class, this method can be refactored not to use generics</strong>
     * @param objectInCart the type of the product, either Product or Movie
     * @param Cart the shopping cart of the product
     * @param quantity the quantity of the product to remove
     * @return empty if the product is not in the shopping cart, otherwise return the quantity of the product before removing
     * @param <T> the type of the product, either Product or Movie
     */
    public <T> Optional<Integer> removeCart(T objectInCart, Map<T, Integer> Cart, int quantity) {
        Integer CartCount = Cart.get(objectInCart);
        if (CartCount == null) {
            return Optional.empty();
        }
        int newCartCount = CartCount - quantity;
        if (newCartCount <= 0) {
            Cart.remove(objectInCart);
        } else {
            Cart.put(objectInCart, newCartCount);
        }
        return Optional.of(CartCount);
    }

}