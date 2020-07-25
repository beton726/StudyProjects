package Yusov.spaceGame.weapons;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.drawObjects.Canvas;

public class Rocket extends BaseObject {
    public Rocket(double x, double y) {
        super(x, y, 1);
    }
    // Двигаем ракету вниз
    @Override
    public void move() {
        y--;
    }
    // Отрисовываем ракету на хосте
    @Override
    public void draw(Canvas canvas) {
        canvas.setPoint(x,y,'R');
    }
}
