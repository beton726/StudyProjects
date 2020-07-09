package yusov.polymorphismExample.secondExample;

public class UBoat implements Swim {
    private int speed;

    public UBoat(int speed) {
        this.speed = speed;
    }

    @Override
    public void swim() {
        System.out.println("Подводная лодка плывет, вращая винты, со скоростью " + speed + " узлов.");
    }
}
