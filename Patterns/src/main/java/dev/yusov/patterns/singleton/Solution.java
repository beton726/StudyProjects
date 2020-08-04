package dev.yusov.patterns.singleton;

/**
 *  Singleton - создание объекта в единственном экземпляре и хранить этот объект в
 *  статической переменной. Запретить создание второго объекта этого класса.
 *  Конструктор - private. Запретили создание объекта Sun везде кроме методов класса Sun.
 *  Получить объект этого класса можно только вызвав метод getInstance().
* */
public class Solution {
    public static void main(String[] args) {
        Sun sun = Sun.getInstance();
    }
}
