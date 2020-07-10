package Yusov.spaceGame;

import Yusov.spaceGame.flyObjects.SpaceShip;
import Yusov.spaceGame.flyObjects.Ufo;
import Yusov.spaceGame.weapons.Bomb;
import Yusov.spaceGame.weapons.Rocket;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Space {

    private static final Logger log = LoggerFactory.getLogger(Space.class.getName());

    private int width;
    private int height;
    private SpaceShip ship;

    private List<Ufo> ufos = new ArrayList<Ufo>();
    private List<Rocket> rockets  = new ArrayList<Rocket>();
    private List<Bomb> bombs  = new ArrayList<Bomb>();

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public static void run() {

    }

    public static void draw() {

    }

    public static void sleep(int ms) {

    }
    // Создание игры
    public static Space game;

    public static void main(String[] args) {


    }
}