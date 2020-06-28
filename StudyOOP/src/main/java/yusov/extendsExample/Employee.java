package yusov.extendsExample;

public class Employee extends Person {

    private String company;

    public Employee(String name, String company) {
        super(name);
        this.company = company;
    }

    public void work() {
        System.out.println(getName() + " works in "+ this.company);
    }

    @Override
    public void printName() {
        super.printName();
        System.out.println("Company: " + this.company);
    }
}