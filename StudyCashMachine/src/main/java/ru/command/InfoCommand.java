package ru.command;

import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;

class InfoCommand implements Command {
    public void execute() {
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(null);
        System.out.println(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
    }
}