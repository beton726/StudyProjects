package ru.model;

import vo.Vacancy;

import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    public List<Vacancy> getVacancies(String searchString) {
        return null;
    }
}