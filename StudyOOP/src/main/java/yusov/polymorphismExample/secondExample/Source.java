package yusov.polymorphismExample.secondExample;

import java.util.Arrays;
import java.util.List;

public class Source {
    public static void main(String[] args) {
        Swim human = new Human("Антон",6);
        Swim fish = new Fish("Кит");
        Swim boat = new UBoat(25);

        List<Swim> swimList = Arrays.asList(human, fish, boat);

        for (Swim name : swimList) {
            name.swim();
        }
    }
}
