package user_in_out.user;

public class Member extends User {
    private Category category;
    private String school;

    public Member(String id, String name, String username, String password, int age) {
        super("member", id, name, username, password, age);
        options = oc.getOptions(this);

        if (age <= 12)
            category = new Child();
        else if (age > 65)
            category = new Elder();
        else
            category = new Adult();
    }

    public Member(String id, String name, String username, String password, int age, String school) {
        super("member", id, name, username, password, age);
        options = oc.getOptions(this);
        this.school = school;
        category = new Student();
    }

    public Category getCategory() { return category; }

    @Override
    public String toString() { 
        if (category.toString().equals("Student"))
            return super.toString() + String.format("%-" + 8 + "s  %s", category, school);
        else
            return super.toString() + category;
    }
}
