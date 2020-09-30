package ru.model;

import ru.view.View;
import vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private View view;
    private Provider[] providers;
    private List<Vacancy> list = new ArrayList<>();

    public Model(View view, Provider... providers) {
        if(view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        } else {
            this.view = view;
            this.providers = providers;
        }
    }

    public void selectCity(String city) {

        for (int i = 0; i < providers.length; i++)
            list.addAll(providers[i].getJavaVacancies(city));
        view.update(list);
    }

}