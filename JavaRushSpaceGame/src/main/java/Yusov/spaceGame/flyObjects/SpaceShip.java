package Yusov.spaceGame.flyObjects;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.Space;
import Yusov.spaceGame.drawObjects.Canvas;
import Yusov.spaceGame.weapons.Rocket;

public class SpaceShip extends BaseObject {

    private double dx = 0;
    private static int[][] matrix = new int[][]{};

    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    public void moveLeft() {
        this.dx = -1;
    }

    public void moveRight() {
        this.dx = 1;
    }

    public void move() {
        checkBorders(0, Space.game.getHeight(), 0, Space.game.getWidth());
        x += dx;
    }
    // Рисует корабль
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'M');
    }
    // Корабль стреляет
    public void fire() {
        Rocket firstRocket = new Rocket(x, y-2);
        Rocket secondRocket = new Rocket(x, y+2);
        Space.game.getRockets().add(firstRocket);
        Space.game.getRockets().add(secondRocket);
    }

}
