package Yusov.spaceGame;

public abstract class BaseObject {

    // Координаты
    protected double x;
    protected double y;
    // Радиус объекта
    protected double radius;
    // Состояние объекта - жив или мёртв
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
    /**
     * Метод рисует свой объект на "канвасе".
     */
    public void draw() {

    }
    /**
     * Двигаем себя на один ход.
     */
    public void move() {

    }
    /**
     * Проверяем - не выходит ли (x,y) за границы.
     */
    public void checkBorders(double minx, double maxx, double miny, double maxy) {
        if(x < minx) x = minx;
        if(x > maxx) x = maxx;
        if(y < miny) y = miny;
        if(y > maxy) y = maxy;
    }

    public void die() {
        this.isAlive = false;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isIntersect(BaseObject o) {
        if(Math.sqrt(Math.pow((o.getX()-this.x),2) + Math.pow((o.getY()-this.y),2)) < Math.max(this.getRadius(), o.getRadius())) {
            return true;
        } else {
            return false;
        }
    }

}