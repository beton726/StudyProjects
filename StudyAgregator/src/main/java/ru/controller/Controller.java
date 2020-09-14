package ru.controller;

import ru.model.Provider;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private Provider[] providers;

    public Controller(Provider... providers) {
        this.providers = providers;
        if(providers.length == 0)
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        for (Provider name : providers) {
            vacancies.addAll(name.getJavaVacancies(null));
        }
        System.out.println(vacancies.size());
    }
}