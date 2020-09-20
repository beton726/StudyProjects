package ru;

import ru.controller.Controller;
import ru.model.Provider;
import ru.model.Strategy;

/*
* Используется паттерн Strategy
*
*
* */
public class Aggregator {
    public static void main(String[] args) {


        Strategy strategy = null;
        Provider provider = new Provider(strategy);

        Controller controller = new Controller(provider);

        controller.scan();
    }
}
