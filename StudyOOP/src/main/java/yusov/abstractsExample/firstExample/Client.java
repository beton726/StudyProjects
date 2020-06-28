package yusov.abstractsExample.firstExample;

public class Client extends Person {

    private String bank;

    public Client(String name, String company) {
        // Ключевое слово super, служит для вызова конструктора суперкласса(родителя)
        super(name);
        this.bank = company;
    }

    @Override
    public void display() {
        System.out.println("Client name: " + super.getName() + " \nBank: " + bank);
    }
}