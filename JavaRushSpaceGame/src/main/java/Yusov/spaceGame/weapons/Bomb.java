package Yusov.spaceGame.weapons;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.drawObjects.Canvas;

public class Bomb extends BaseObject {
    public Bomb(double x, double y) {
        super(x, y, 1);
    }
    // Двигаем бомбу вниз
    @Override
    public void move() {
        y++;
    }
    // Отрисовываем бомбу на хосте
    @Override
    public void draw(Canvas canvas) {
        canvas.setPoint(x,y,'B');
    }
}
