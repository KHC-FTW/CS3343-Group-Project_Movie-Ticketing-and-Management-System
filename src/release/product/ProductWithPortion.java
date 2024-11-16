package release.product;

/**
 * Interface for all products with portion
 */
public interface ProductWithPortion extends Product {
    /**
     * get the portion of the product
     *
     * @return the portion of the product
     */
    String getPortion();

    /**
     * set the portion of the product, and return the portion set
     *
     * @param portion portion of the product
     * @return the portion of the product set
     */
    String setPortion(String portion);

    boolean equals(Object product);
}
