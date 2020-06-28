package yusov.abstractExample.firstExample;

public class Employee extends Person {

    private String bank;

    public Employee(String name, String company) {
        // Ключевое слово super, служит для вызова конструктора суперкласса(родителя)
        super(name);
        this.bank = company;
    }

    @Override
    public void display() {
        System.out.println("Employee name: " + super.getName() + " \nBank: " + bank);
    }
}