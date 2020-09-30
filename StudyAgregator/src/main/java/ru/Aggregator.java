package ru;

import ru.controller.Controller;
import ru.model.HHStrategy;
import ru.model.Model;
import ru.model.Provider;
import ru.model.Strategy;
import ru.view.HtmlView;
import ru.view.View;

/*
* Используется паттерн Strategy
*
*
* */
public class Aggregator {
    public static void main(String[] args) {

        Strategy strategy = new HHStrategy();

        Provider provider = new Provider(strategy);

        HtmlView view = new HtmlView();

        Model model = new Model(view, provider);

        Controller controller = new Controller(model);

        view.setController(controller);

        view.userCitySelectEmulationMethod();

    }
}