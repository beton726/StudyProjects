package ru.model;

import vo.Vacancy;

import java.util.Collections;
import java.util.List;

public class Provider {
    public Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        if(searchString == null)
            return Collections.EMPTY_LIST;
        return strategy.getVacancies(searchString);
    }

}