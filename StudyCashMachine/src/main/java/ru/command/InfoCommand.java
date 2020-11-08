package ru.command;

import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Collection;

class InfoCommand implements Command {
    public void execute() {

        if(CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            ConsoleHelper.writeMessage("No money available.");
        } else {
            Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
            for (CurrencyManipulator word : currencyManipulators) {
                if(word.hasMoney())
                    ConsoleHelper.writeMessage(word.getCurrencyCode() + " - " + word.getTotalAmount());
            }
        }
    }
}