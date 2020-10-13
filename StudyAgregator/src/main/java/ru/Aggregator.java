package ru;

import ru.controller.Controller;
import ru.model.*;
import ru.view.HtmlView;
import ru.view.View;

/*
* Используется паттерн Strategy
*
*
* */
public class Aggregator {
    public static void main(String[] args) {

        View view = new HtmlView();
        Provider[] providers = {new Provider(new HHStrategy() {}),new Provider(new MoikrugStrategy() {})};
        Model model = new Model(view,providers);
        Controller controller = new Controller(model);
        view.setController(controller);


    }
}