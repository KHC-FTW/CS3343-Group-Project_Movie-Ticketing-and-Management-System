package release.user;


import release.record.PaymentRecord;
import release.shoppingCart.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private Category category;
    private String school;
    private final ShoppingCart shoppingCart;
    private final List<PaymentRecord> paymentRecords;

    public Member(String id, String name, String username, String password, int age) {
        super("member", id, name, username, password, age);
        options = oc.getOptions(this);

        if (age < 18)
            category = Child.getInstance();
        else if (age >= 65)
            category = Elder.getInstance();
        else
            category = Adult.getInstance();

        this.shoppingCart = new ShoppingCart();
        this.paymentRecords = new ArrayList<>();
    }

    public Member(String id, String name, String username, String password, int age, String school) {
        super("member", id, name, username, password, age);
        options = oc.getOptions(this);
        this.school = school;
        category = Student.getInstance();
        this.shoppingCart = new ShoppingCart();
        this.paymentRecords = new ArrayList<>();
    }

    public Category getCategory() { return category; }

    public ShoppingCart getShoppingCart() {return shoppingCart;}

    public List<PaymentRecord> getPaymentRecords(){return paymentRecords;}

    public void addPaymentRecord(PaymentRecord paymentRecord){
        this.paymentRecords.add(paymentRecord);
    }

    @Override
    public String toString() { 
        if (category.toString().equals("Student"))
            return super.toString() + String.format("%-" + 8 + "s  %s", category, school);
        else
            return super.toString() + category;
    }
}
