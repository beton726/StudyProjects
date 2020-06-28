package yusov.abstractsExample.secondExample;

public class Rectangle extends Figure {

    private float width;
    private float height;

    public Rectangle(float x, float y, float width, float height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public float sum() {
        return super.getFigureSum();
    }

    @Override
    public float getPerimetr() {
        return width*2 + height*2;
    }

    @Override
    public float getArea() {
        return width*height;
    }
}