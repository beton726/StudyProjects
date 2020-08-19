package src.game;

import src.field.Field;
import src.figure.Figure;

public class Tetris {

    public static Tetris game;
    private Field field;
    private Figure figure;

    public void setField(Field field) {
        this.field = field;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run() {

    }

    public void step() {

    }

    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        game = tetris;
        game.run();
    }
}
