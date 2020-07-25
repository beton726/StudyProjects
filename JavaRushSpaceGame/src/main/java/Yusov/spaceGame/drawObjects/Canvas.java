package Yusov.spaceGame.drawObjects;

public class Canvas {

    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width,int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height][width];
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMatrix() {
        this.matrix = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setPoint(double x, double y, char c) {
        int x0 = (int) Math.round(x);
        int y0 = (int) Math.round(y);
        if (y0 < 0 || y0 >= matrix.length) return;
        if (x0 < 0 || x0 >= matrix[y0].length) return;
        matrix[y0][x0] = c;
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] != 0) {
                    setPoint(x+j,y+i,c);
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = ' ';
            }
        }
    }

    public void print() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" ");
                System.out.print(matrix[i][j] + " ");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
}