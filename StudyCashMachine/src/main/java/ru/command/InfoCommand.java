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
            // Вывести для каждого манипулятора "код валюты - общая сумма денег для выбранной валюты".
            // Если денег нет в банкомате или
            CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        }
    }
}