package Yusov.spaceGame;

import Yusov.spaceGame.drawObjects.Canvas;
import Yusov.spaceGame.flyObjects.SpaceShip;
import Yusov.spaceGame.flyObjects.Ufo;
import Yusov.spaceGame.weapons.Bomb;
import Yusov.spaceGame.weapons.Rocket;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import jdk.nashorn.internal.ir.CallNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Space {

    private static final Logger log = LoggerFactory.getLogger(Space.class.getName());

    // Создание игры
    public static Space game;

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

    /**
     * Основной цикл программы.
     * Тут происходят все важные действия
     */
    public void run() {
        //Создаем холст для отрисовки.
        Canvas canvas = new Canvas(width, height);
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();
        //Игра работает, пока корабль жив
        while (ship.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если "стрелка влево" - сдвинуть фигурку влево
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                    //Если "пробел" - запускаем шарик
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }
            //двигаем все объекты игры
            moveAllItems();
            //проверяем столкновения
            checkBombs();
            checkRockets();
            //удаляем умершие объекты из списков
            removeDead();
            //Создаем НЛО (1 раз в 10 ходов)
            createUfo();
            //Отрисовываем все объекты на холст, а холст выводим на экран
            canvas.clear();
            draw(canvas);
            canvas.print();
            //Пауза 300 миллисекунд
            Space.sleep(300);
        }
        //Выводим сообщение "Game Over"
        System.out.println("Game Over!");
    }

    public void draw(Canvas canvas) {
        //draw game
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }
        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }
        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
        for (BaseObject object : getAllItems()) {
            object.draw(canvas);
        }
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
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
        double x = Math.random()*20;
        double y = Math.random()*10;
        if(ufos.isEmpty()) {
            Ufo ufo = new Ufo(x,y);
            ufos.add(ufo);
        }
    }

    public void checkBombs() {
        for (Bomb nameBomb : bombs) {
            if(nameBomb.isIntersect(ship)) {
                nameBomb.die();
                ship.die();
            } else if(nameBomb.getY() >= height) {
                nameBomb.die();
            }
        }
    }
    
    public void checkRockets() {
        for (Rocket nameRocket : rockets) {
            for (Ufo nameUfo : ufos) {
                if(nameRocket.isIntersect(nameUfo)) {
                    nameRocket.die();
                    nameUfo.die();
                } else if(nameRocket.getY() <= 0)
                    nameRocket.die();
            }
        }
    }

    public void removeDead() {
        ufos.removeIf(value -> !value.isAlive());
        rockets.removeIf(value -> !value.isAlive());
        bombs.removeIf(value -> !value.isAlive());
    }

    public static void main(String[] args) {

        game = new Space(20,20);
        game.setShip(new SpaceShip(10,16));
        game.run();

    }
} 