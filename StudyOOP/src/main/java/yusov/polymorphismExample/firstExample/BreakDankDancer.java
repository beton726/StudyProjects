package yusov.polymorphismExample.firstExample;

public class BreakDankDancer extends Dancer{

    public BreakDankDancer(String name, int age) {
        super(name, age);
    }

    @Override
    public void dance(){
        System.out.println(toString() + "Я танцую брейк-данс!");
    }
}
