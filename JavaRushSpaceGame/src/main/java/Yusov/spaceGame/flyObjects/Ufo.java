package Yusov.spaceGame.flyObjects;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.Space;
import Yusov.spaceGame.drawObjects.Canvas;
import Yusov.spaceGame.weapons.Bomb;

public class Ufo extends BaseObject {

    private double dx = Math.random()*2-1; // -1...1
    private double dy = Math.random()*2-1; // -1...1
    private static int[][] matrix = new int[][]{};

    public Ufo(double x, double y) {
        super(x, y, 3);
    }
    // Рисует инопланетный корабль
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'U');
    }

    public void move() {
        x += dx;
        y += dy;

        checkBorders(0, Space.game.getHeight(), 0, Space.game.getWidth()/2);

        if(y <= Space.game.getHeight())
            y = y+1;

        if(Math.random()*1>0.9) {
            fire();
        }
    }

    public void fire() {
        Bomb bomb = new Bomb(x,y+radius);
        Space.game.getBombs().add(bomb);
    }
}
