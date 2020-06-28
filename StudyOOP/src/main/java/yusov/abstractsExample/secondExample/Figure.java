package yusov.abstractsExample.secondExample;

public abstract class Figure {
    float x;
    float y;

    public Figure(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getFigureSum() {
        return x + y;
    }

    public abstract float getPerimetr();

    public abstract float getArea();
}