package test.record_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.exception.ExInvalidSeatingPlan;
import release.exception.ExNoPaymentRecord;
import release.externalAPI.ExternalAPIFactory;
import release.externalAPI.OctopusAPIFactory;
import release.movie.House;
import release.movie.Movie;
import release.movie.MovieSession;
import release.payment.OctopusPaymentFactory;
import release.payment.Payment;
import release.payment.PaymentFactory;
import release.product.MovieTicket;
import release.product.Product;
import release.product.Snack;
import release.record.PaymentRecord;
import release.user.Member;

import java.util.*;

public class PaymentRecordTest {
    Member testMember;
    Payment testPayment;
    Snack testSnack;
    MovieTicket testMovieTicket;
    Movie testMovie;
    MovieSession testMovieSession;
    Map<Product, Integer> testProductList;
    List<MovieTicket> testMovieTicketList;
    PaymentFactory paymentFactory = new OctopusPaymentFactory();
    ExternalAPIFactory externalAPIFactory = new OctopusAPIFactory();

    @BeforeEach
    void setUp() throws ExInvalidSeatingPlan {
        testMember = new Member("test_id", "test_name", "test_user", "test_password", 100, "test_school");
        testPayment = paymentFactory.createPaymentMethod(externalAPIFactory.getExternalAPI(new Random(10)));
        testSnack = new Snack("snack", 10, "10g");
        testMovie = new Movie("test_movie", "testing", 999, 999, 10, "I", "English", "English");
        testMovieSession = new MovieSession(testMovie, "10:00", "12:00", new House());
        testMovieTicket = new MovieTicket(testMovie, testMovieSession, "A1");
        testProductList = new HashMap<>(Map.of(testSnack, 1));
        testMovieTicketList = new ArrayList<>(List.of(testMovieTicket));
    }

    @Test
    void test_constructor() {
        PaymentRecord paymentRecord = new PaymentRecord(testMember, testProductList, testMovieTicketList, testPayment);
        Assertions.assertEquals(testMember, paymentRecord.user());
        Assertions.assertEquals(testProductList, paymentRecord.productList());
        Assertions.assertEquals(testMovieTicketList, paymentRecord.movieTicketList());
        Assertions.assertEquals(testPayment, paymentRecord.payment());
    }

    @Test
    void test_ListModifyOutside() {
        PaymentRecord paymentRecord = new PaymentRecord(testMember, testProductList, testMovieTicketList, testPayment);
        testProductList.clear();
        testMovieTicketList.clear();
        Assertions.assertFalse(paymentRecord.productList().isEmpty());
        Assertions.assertFalse(paymentRecord.movieTicketList().isEmpty());
    }

    @Test
    void test_showAllPaymentRecords() throws ExNoPaymentRecord {
        PaymentRecord paymentRecord = new PaymentRecord(testMember, testProductList, testMovieTicketList, testPayment);
        List<PaymentRecord> paymentRecords = new ArrayList<>(List.of(paymentRecord));
        Assertions.assertDoesNotThrow(() -> PaymentRecord.showAllPaymentRecords(paymentRecords));
        final int lineSeparator = 73;
        String expected = String.format("Payment Record 1 (OCTOPUS):%n");
        String formattedMovieTicket = MovieTicket.formatMovieTicketList(testMovieTicketList);
        String snackTitle = String.format("\n%4s%-32s%-16s%-15s%-6s\n%s\n", " ", "Snacks/Drinks", "Portion", "Quantity",
                "Price", "-".repeat(lineSeparator));
        String formattedSnack = String.format("%2d) %-30s%2s%-16s%5d%10s$%-4.1f\n", 1, testSnack.getName(), " ",
                (testSnack).getPortion(), 1, " ", testSnack.getPrice());
        expected += formattedMovieTicket + snackTitle + formattedSnack + "-".repeat(
                lineSeparator) + "\n" + "~".repeat(lineSeparator) + "\n\n";
        Assertions.assertEquals(expected, PaymentRecord.showAllPaymentRecords(paymentRecords));
    }

    @Test
    void test_showAllPaymentRecordsEmpty() {
        List<PaymentRecord> paymentRecords = new ArrayList<>();
        Exception exception = Assertions.assertThrows(ExNoPaymentRecord.class,
                () -> PaymentRecord.showAllPaymentRecords(paymentRecords));
        Assertions.assertEquals("[Exception] There is no payment record to show.", exception.getMessage());
    }

    @Test
    void test_emptyProductList() throws ExNoPaymentRecord {
        PaymentRecord paymentRecord = new PaymentRecord(testMember, new HashMap<>(), new ArrayList<>(), testPayment);
        List<PaymentRecord> paymentRecords = new ArrayList<>(List.of(paymentRecord));
        Assertions.assertDoesNotThrow(() -> PaymentRecord.showAllPaymentRecords(paymentRecords));
        Assertions.assertEquals(String.format("Payment Record 1 (OCTOPUS):%n") + "~".repeat(73) + "\n\n",
                PaymentRecord.showAllPaymentRecords(paymentRecords));
    }
}