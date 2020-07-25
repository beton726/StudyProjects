package Yusov.spaceGame;

import Yusov.spaceGame.flyObjects.SpaceShip;
import Yusov.spaceGame.flyObjects.Ufo;
import Yusov.spaceGame.weapons.Bomb;
import Yusov.spaceGame.weapons.Rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<BaseObject> getAllItems() {
        List<BaseObject> allList = new ArrayList<BaseObject>();

        allList.addAll(ufos);
        allList.addAll(rockets);
        allList.addAll(bombs);
        allList.add(ship);

        return allList;
    }

    public void moveAllItems() {
        for (BaseObject name : getAllItems()) {
            name.move();
        }
    }

    public void createUfo() {
        if(ufos.isEmpty()) {
            Ufo ufo = new Ufo(0,0);
        }
    }

    public void checkBombs() {
        for (Bomb nameBomb : bombs) {
            nameBomb.isIntersect(ship);
        }
    }

    public void checkRockets() {

    }

    public void removeDead() {

    }

    // Создание игры
    public static Space game;

    public static void main(String[] args) {



    }
} 