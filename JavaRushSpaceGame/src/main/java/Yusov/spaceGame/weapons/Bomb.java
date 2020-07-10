package Yusov.spaceGame.weapons;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.drawObjects.Canvas;

public class Bomb extends BaseObject {

    public Bomb(double x, double y) {
        super(x, y, 1);
    }

    public void move() {
        double y = getY();
        setY(y+1);
    }

    public void draw(Canvas canvas) {
        canvas.setPoint(getX(),getY(),'B');
    }

}
