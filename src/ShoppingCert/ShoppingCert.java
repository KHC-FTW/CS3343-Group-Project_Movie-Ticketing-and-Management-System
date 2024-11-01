package ShoppingCert;

import Product.Product;
import selectMovieModule.Movie;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ShoppingCert class that contains all products in the shopping cart
 */
public class ShoppingCert {
    private final Map<Product, Integer> productCert;
    private final Map<Movie, Integer> movieCert;

    /**
     * Default constructor for ShoppingCert class, which initializes the shopping cert of products and movies
     */
    public ShoppingCert() {
        productCert = new HashMap<>();
        movieCert = new HashMap<>();
    }

    /**
     * Constructor for ShoppingCert class, which the cert is initialized with the hashmap of products and movies passed into the constructor
     *
     * @param productCert : products in the shopping cart
     * @param movieCert   : movies in the shopping cart
     */
    public ShoppingCert(HashMap<Product, Integer> productCert, HashMap<Movie, Integer> movieCert) {
        this.productCert = productCert;
        this.movieCert = movieCert;
    }

    /**
     * Add a product to the shopping cart
     *
     * @param product : product to add to the shopping cart
     */
    public void addProduct(Product product) {
        int productCount = productCert.getOrDefault(product, 0);
        productCert.put(product, productCount + 1);
    }

    /**
     * Add a product to the shopping cart with a specific quantity
     *
     * @param product  : product to add to the shopping cart
     * @param quantity : quantity of the product to add to the shopping cart
     */
    public void addProduct(Product product, int quantity) {
        int productCount = productCert.getOrDefault(product, 0);
        productCert.put(product, productCount + quantity);
    }

    /**
     * Get the shopping cart of products
     *
     * @return the products in the shopping cart in Map
     */
    public Map<Product, Integer> getProductCert() {
        return productCert;
    }

    /**
     * Add a movie to the shopping cart
     *
     * @param movie : movie to add to the shopping cart
     */
    public void addMovie(Movie movie) {
        int movieCount = movieCert.getOrDefault(movie, 0);
        movieCert.put(movie, movieCount + 1);
    }

    /**
     * Add a movie to the shopping cart with a specific quantity
     *
     * @param movie    : movie to add to the shopping cart
     * @param quantity : quantity of the movie to add to the shopping cart
     */
    public void addMovie(Movie movie, int quantity) {
        int movieCount = movieCert.getOrDefault(movie, 0);
        movieCert.put(movie, movieCount + quantity);
    }

    /**
     * Get the shopping cart of movies
     *
     * @return the movies in the shopping cart in Map
     */
    public Map<Movie, Integer> getMovieCert() {
        return movieCert;
    }

    /**
     * Clear all shopping cart
     */
    public void clearAllCert() {
        productCert.clear();
        movieCert.clear();
    }

    /**
     * clear the shopping cart of products
     */
    public void clearProductCert() {
        productCert.clear();
    }

    /**
     * clear the shopping cart of movies
     */
    public void clearMovieCert() {
        movieCert.clear();
    }
    
    /**
     * Remove a product from the shopping cart by decreasing the quantity by the number passed into the method
     * If the quantity of the product is smaller than or equal to 0 after the quantity is decreased, remove the product from the shopping cart<br>
     * <strong>Note: If movie and product are modified so that they have the same parent class, this method can be refactored not to use generics</strong>
     * @param objectInCert the type of the product, either Product or Movie
     * @param cert the shopping cart of the product
     * @param quantity the quantity of the product to remove
     * @return empty if the product is not in the shopping cart, otherwise return the quantity of the product before removing
     * @param <T> the type of the product, either Product or Movie
     */
    public <T> Optional<Integer> removeCert(T objectInCert, Map<T, Integer> cert, int quantity) {
        Integer certCount = cert.get(objectInCert);
        if (certCount == null) {
            return Optional.empty();
        }
        int newCertCount = certCount - quantity;
        if (newCertCount <= 0) {
            cert.remove(objectInCert);
        } else {
            cert.put(objectInCert, newCertCount);
        }
        return Optional.of(certCount);
    }

}