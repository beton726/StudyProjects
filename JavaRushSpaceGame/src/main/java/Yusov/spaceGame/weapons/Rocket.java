package Yusov.spaceGame.weapons;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.drawObjects.Canvas;

public class Rocket extends BaseObject {
    public Rocket(double x, double y) {
        super(x, y, 1);
    }
    // Двигаем ракету вниз
    public void move() {
        y--;
    }
    // Отрисовываем ракету на хосте
    public void draw(Canvas canvas) {
        canvas.setPoint(getX(),getY(),'R');
    }
}
