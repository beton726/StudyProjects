package ru.command;

import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Collection;

class InfoCommand implements Command {
    public void execute() {
        if(!CurrencyManipulator.hasMoney()) {
            ConsoleHelper.writeMessage("No money available.");
        } else {
            // Возвращает true, если общая сумма данной валюты > 0
            // Вывести для каждого манипулятора "код валюты - общая сумма денег для выбранной валюты".
            Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
            for (CurrencyManipulator word : currencyManipulators) {
                if(word.getTotalAmount() > 0)
                    ConsoleHelper.writeMessage(word.getCurrencyCode() + " - " + word.getTotalAmount());
                else
                    ConsoleHelper.writeMessage("No money available.");
            }
        }
    }
}