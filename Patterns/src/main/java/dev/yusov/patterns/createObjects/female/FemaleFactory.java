package dev.yusov.patterns.createObjects.female;

import dev.yusov.patterns.createObjects.AbstractFactory;
import dev.yusov.patterns.createObjects.Human;

public class FemaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if(age <= KidGirl.MAX_AGE) {
            return new KidGirl();
        } else if (age <= TeenGirl.MAX_AGE) {
            return new TeenGirl();
        } else
            return new Woman();
    }

}
