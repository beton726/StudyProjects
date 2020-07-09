package yusov.polymorphismExample.firstExample;

public class ElectricBoogieDancer extends Dancer{
    public ElectricBoogieDancer(String name, int age) {
        super(name, age);
    }

    @Override
    public void dance() {
        System.out.println(toString() + "Я танцую электрик буги!");
    }
}