package Product;


/**
 * Interface for all products
 */
public interface Product {
    /**
     * get the name of the product
     *
     * @return the name of the product
     */
    String getName();



    /**
     * get the price of the product
     *
     * @return the price of the product
     */
    double getPrice();
    

    /**
     * compare the product with another object
     *
     * @param o object to compare
     * @return true if the product is equal to the object, false otherwise
     */
    boolean equals(Object o);

    /**
     * get the hash code of the product
     *
     * @return the hash code of the product
     */
    int hashCode();
}

