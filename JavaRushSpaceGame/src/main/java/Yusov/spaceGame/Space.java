package Yusov.spaceGame;

import Yusov.spaceGame.flyObjects.SpaceShip;
import Yusov.spaceGame.flyObjects.Ufo;
import Yusov.spaceGame.weapons.Bomb;
import Yusov.spaceGame.weapons.Rocket;

import java.util.ArrayList;
import java.util.List;

public class Space {

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
        return this.ship;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<Ufo> getUfos() {
        return this.ufos;
    }

    public List<Rocket> getRockets() {
        return this.rockets;
    }

    public List<Bomb> getBombs() {
        return this.bombs;
    }

    public static void run() {

    }

    public static void draw() {

    }

    public static void sleep(int ms) {

    }

    public static void main(String[] args) {
    }
}