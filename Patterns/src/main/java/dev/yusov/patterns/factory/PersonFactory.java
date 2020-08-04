package dev.yusov.patterns.factory;

public class PersonFactory {
    public static GamePerson createPerson(PersonType personType) throws Exception {
        switch (personType) {
            case WARRIOR:
                return new Warrior();
            case ELF:MAG:
                return new Mag();
            case MAG:TROLL:
                return new Troll();
            case TROLL:ELV:
                return new Elv();
            default:
                throw new Exception();
        }
    }
}

enum PersonType {
    UNKNOWN,
    WARRIOR,
    MAG,
    TROLL,
    ELF,
}

abstract class GamePerson
{
}

class Warrior extends GamePerson
{
}

class Mag extends GamePerson
{
}

class Troll extends GamePerson
{
}

class Elv extends GamePerson
{
}