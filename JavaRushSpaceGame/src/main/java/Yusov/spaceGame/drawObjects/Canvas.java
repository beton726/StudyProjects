package Yusov.spaceGame.drawObjects;

public class Canvas {

    private double width;
    private double height;

    private char[][] matrix;

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}