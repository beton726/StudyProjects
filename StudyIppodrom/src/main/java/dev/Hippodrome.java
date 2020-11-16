package dev;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }
    // Забег
    void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(500L);
            move();
            print();
        }
    }

    void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        double winner = 0;
        Horse winnerHorse = null;
        for (Horse horse : horses) {
            if(winner < horse.getDistance()) {
                winner = horse.getDistance();
                winnerHorse = horse;
            }
        }
        return winnerHorse;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        List<Horse> list = new ArrayList<Horse>();
        list.add(new Horse("First", 3, 0));
        list.add(new Horse("Second", 3, 0));
        list.add(new Horse("Third", 3, 0));

        game = new Hippodrome(list);

        game.run();

        game.printWinner();
    }

}