package dev.yusov.patterns.createObjects.male;

import dev.yusov.patterns.createObjects.AbstractFactory;
import dev.yusov.patterns.createObjects.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if(age <= KidBoy.MAX_AGE) {
            return new KidBoy();
        } else if (age <= TeenBoy.MAX_AGE) {
            return new TeenBoy();
        } else
            return new Man();
    }

}
