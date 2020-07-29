package Yusov.spaceGame.flyObjects;

import Yusov.spaceGame.BaseObject;
import Yusov.spaceGame.Space;
import Yusov.spaceGame.drawObjects.Canvas;
import Yusov.spaceGame.weapons.Rocket;

public class SpaceShip extends BaseObject {

    private double dx = 0;
    // Картинка корабля для отрисовки
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1},
    };

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
        x += dx;
        checkBorders(radius, Space.game.getWidth() - radius + 1, 1, Space.game.getHeight() + 1);
    }
    // Рисует корабль
    public void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y - radius + 1, matrix, 'M');
    }
    // Корабль стреляет
    public void fire() {
        Rocket firstRocket = new Rocket(x-2 , y);
        Rocket secondRocket = new Rocket(x+2, y);
        Space.game.getRockets().add(firstRocket);
        Space.game.getRockets().add(secondRocket);
    }

}