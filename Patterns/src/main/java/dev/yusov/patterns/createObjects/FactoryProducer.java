package dev.yusov.patterns.createObjects;

import dev.yusov.patterns.createObjects.female.FemaleFactory;
import dev.yusov.patterns.createObjects.male.MaleFactory;

public class FactoryProducer {

    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type) {
        if(type == type.MALE)
            return new MaleFactory();
        else if(type == type.FEMALE)
            return new FemaleFactory();
        else
            return null;
    }

}