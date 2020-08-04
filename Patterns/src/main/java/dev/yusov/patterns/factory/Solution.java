package dev.yusov.patterns.factory;

/**
 *  Factory
 *  Например имеется куча классов: Warrior, Mag, Troll, Elv, все они унаследованы от GamePerson.
 *  Вопрос: Как гибко управлять созданием объектов этих классов?
 *  1) Заводим Enum
 *  2) Создание специального класса Factory, у которого будет статический метод или методы, которые
 *  и будут заниматься созданием объектов в зависимости от Enum.
 *  Можно использовать например 3 класса и создавать много различных объектов.
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        GamePerson gamePerson = PersonFactory.createPerson(PersonType.MAG);
    }
}
