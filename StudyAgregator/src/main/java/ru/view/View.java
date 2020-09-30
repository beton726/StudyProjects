package ru.view;

import ru.controller.Controller;
import vo.Vacancy;

import java.util.List;

public interface View {

    public void update(List<Vacancy> vacancies);

    public void setController(Controller controller);
}