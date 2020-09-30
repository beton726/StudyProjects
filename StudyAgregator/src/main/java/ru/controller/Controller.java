package ru.controller;

import ru.model.Model;
import ru.model.Provider;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private Model model;

    public Controller(Model model) {
        if(model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        System.out.println("CONTROLLER onCitySelect");
        model.selectCity(cityName);
    }

}