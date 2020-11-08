package ru.command;

import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

class InfoCommand implements Command {
    public void execute() {
        // По какой валюте мы хотим вывести данные?
        // Вводим валюту
        String code = ConsoleHelper.askCurrencyCode();
        // Как определяется есть ли по данной валюте хоть что-то?
        if(!CurrencyManipulatorFactory.hasMoney()) {
            System.out.println("No money available.");
        } else {
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

            System.out.println(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
        }
    }
}