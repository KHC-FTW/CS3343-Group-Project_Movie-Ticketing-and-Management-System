package TestRecord;

import Payment.Payment;
import Record.PaymentRecord;
import Product.Product;
import Product.Snack;
import Payment.PaymentType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import selectMovieModule.CustomException;
import selectMovieModule.Customer;
import selectMovieModule.Movie;

import java.util.List;

public class PaymentRecordTest {
    PaymentRecord paymentRecord;

    class PaymentStub implements Payment {
        /**
         * @param price
         * @return
         */
        @Override
        public boolean doPayment(int price) {
            return false;
        }

        /**
         * @return
         */
        @Override
        public PaymentType getPaymentType() {
            return null;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    class CustomerStub extends Customer {
        public CustomerStub(String userName, String password, int age) throws CustomException {
            super(userName, password, age);
        }

        @Override
        public int getAge() {
            return 100;
        }
    }

    @BeforeEach
    void setUp() throws CustomException {
        Customer customer = new CustomerStub("John", "Doe", 100);
        List<Movie> movieList = List.of(new Movie("movie1", "action", 123, 100, "I", "English", "English"));
        List<Product> productList = List.of(new Snack("snack1", 10, "100g"));
        paymentRecord = new PaymentRecord(customer,
                productList,
                movieList,
                new PaymentStub());
    }

    @Test
    void testPaymentRecord() {
        Assertions.assertEquals("John", paymentRecord.customer().getUserName());
        Assertions.assertEquals("Doe", paymentRecord.customer().getPassword());
        Assertions.assertEquals(100, paymentRecord.customer().getAge());
        Movie movie = new Movie("movie1", "action", 123, 100, "I", "English", "English");
        Assertions.assertEquals(movie, paymentRecord.movieList().getFirst());
        Assertions.assertTrue(paymentRecord.payment().equals(new PaymentStub()));

    }

    @Test
    void testGetAllRecord() {
        List<PaymentRecord> allRecord = PaymentRecord.getAllRecord();
        Assertions.assertEquals("John", allRecord.getFirst().customer().getUserName());
        Assertions.assertEquals("Doe", allRecord.getFirst().customer().getPassword());
        Assertions.assertEquals(100, allRecord.getFirst().customer().getAge());
        Movie movie = new Movie("movie1", "action", 123, 100, "I", "English", "English");
        Assertions.assertEquals(movie, allRecord.getFirst().movieList().getFirst());
        Assertions.assertTrue(allRecord.getFirst().payment().equals(new PaymentStub()));
    }
}
