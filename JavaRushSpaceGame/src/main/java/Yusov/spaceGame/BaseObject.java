package Yusov.spaceGame;

public abstract class BaseObject {

    private double x;
    private double y;
    private double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isAlive = true;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void draw() {

    }

    public void move() {

    }

    public void die() {
        this.isAlive = false;
    }

    public boolean isIntersect(BaseObject o) {
        if(Math.sqrt(Math.pow((o.getX()-this.x),2) + Math.pow((o.getY()-this.y),2)) < Math.max(this.getRadius(), o.getRadius())) {
            return true;
        } else {
            return false;
        }
    }

}