package ru.command;

import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

class InfoCommand implements Command {
    public void execute() {
        // Вводим валюту
        String code = ConsoleHelper.askCurrencyCode();
        // Как определяется есть ли по данной валюте хоть что-то?
        if(!CurrencyManipulatorFactory.hasMoney()) {
            System.out.println("No money available.");
        } else {
            // Возвращает true, если общая сумма данной валюты > 0
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
            // По валюте достаём номинал и их количество
            if(currencyManipulator.getTotalAmount() > 0) {
                System.out.println(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
            } else
                System.out.println("No money available.");
        }
    }
}