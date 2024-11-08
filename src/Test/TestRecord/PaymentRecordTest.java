package TestRecord;

import Payment.Payment;
import Payment.PaymentStatus;
import Record.PaymentRecord;
import Product.Product;
import Product.Snack;
import Product.MovieTicket;
import Payment.PaymentType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.*;

import java.util.List;

/**
 * PaymentRecordTest class that contains the test cases for PaymentRecord class
 */
public class PaymentRecordTest {
    PaymentRecord paymentRecord;

    /**
     * PaymentStub class that implements Payment interface for testing purposes
     */
    class PaymentStub implements Payment {
        /**
         * @param price price of the product
         * @return false to simulate the payment process
         */
        @Override
        public boolean doPayment(double price) {
            return false;
        }

        /**
         * Get the payment type
         *
         * @return null for testing purposes
         */
        @Override
        public PaymentType getPaymentType() {
            return null;
        }

        /**
         * Get the payment status 
         * @return null for testing purposes
         */
        @Override
        public PaymentStatus getPaymentStatus() {
            return null;
        }

        /**
         * Check if the payment is equal to another object
         *
         * @param obj object to compare
         * @return always true for testing purposes
         */
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    /**
     * CustomerStub class that extends Customer class for testing purposes
     */
    class CustomerStub extends Customer {
        /**
         * Constructor for CustomerStub class
         *
         * @param userName username of the customer
         * @param password password of the customer
         * @param age      age of the customer
         * @throws CustomException if the age is invalid
         */
        public CustomerStub(String userName, String password, int age) throws CustomException {
            super(userName, password, age);
        }

        /**
         * Get the age of the customer
         *
         * @return 100 for testing purposes
         */
        @Override
        public int getAge() {
            return 100;
        }
    }

    private MovieTicket movieTicket;

    /**
     * Set up the test environment
     *
     * @throws CustomException if the age is invalid
     */
    @BeforeEach
    void setUp() throws CustomException {
        Customer customer = new CustomerStub("John", "Doe", 100);
        Movie movie = new Movie("movie1", "action", 123, 100, 100, "I", "English", "English");
        MovieSession movieSession = new MovieSession(movie, "11:00", "12:00", new House());

        movieTicket = new MovieTicket(movie, movieSession, "A1");
        List<MovieTicket> movieTicketList = List.of(movieTicket);
        List<Product> productList = List.of(new Snack("snack1", 10, "100g"));
        paymentRecord = new PaymentRecord(customer,
                productList,
                movieTicketList,
                new PaymentStub());
    }

    /**
     * Test the payment record, get the customer, movie list and payment and assert them
     */
    @Test
    void testPaymentRecord() {
        Assertions.assertEquals("John", paymentRecord.customer().getUserName());
        Assertions.assertEquals("Doe", paymentRecord.customer().getPassword());
        Assertions.assertEquals(100, paymentRecord.customer().getAge());
        Movie movie = new Movie("movie1", "action", 123, 100, 100, "I", "English", "English");
        Assertions.assertEquals(movieTicket, paymentRecord.movieTicketList().getFirst());
        Assertions.assertTrue(paymentRecord.payment().equals(new PaymentStub()));

    }

    /**
     * Test the get all record method, get all the payment records and assert them
     */
    @Test
    void testGetAllRecord() {
        List<PaymentRecord> allRecord = PaymentRecord.getAllRecord();
        Assertions.assertEquals("John", allRecord.getFirst().customer().getUserName());
        Assertions.assertEquals("Doe", allRecord.getFirst().customer().getPassword());
        Assertions.assertEquals(100, allRecord.getFirst().customer().getAge());
        Movie movie = new Movie("movie1", "action", 123, 100, 100, "I", "English", "English");
        Assertions.assertEquals(movieTicket, allRecord.getFirst().movieTicketList().getFirst());
        Assertions.assertTrue(allRecord.getFirst().payment().equals(new PaymentStub()));
    }
}
