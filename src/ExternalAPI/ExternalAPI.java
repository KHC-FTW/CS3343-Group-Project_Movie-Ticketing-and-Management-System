package ExternalAPI;

/**
 * ExternalAPI interface
 * It is used to simulate the payment process of the external API
 * <br><strong>Note: The constructor for the class which implements this interface should be set to package private by not adding any modifier, as they should be created using the corresponding factory method</strong> * ExternalAPI interface
 * It is used to simulate the payment process of the external API
 * <br><strong>Note: The constructor for the class which implements this interface should be set to package private by not adding any modifier, as they should be created using the corresponding factory method</strong>
 */
public interface ExternalAPI {
    boolean doPayment(double price);
}
