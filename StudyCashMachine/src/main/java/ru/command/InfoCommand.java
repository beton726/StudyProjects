package ru.command;

import ru.CashMachine;
import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.info_en");

    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean money = false;
        for (CurrencyManipulator cur : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (cur.hasMoney()) {
                if (cur.getTotalAmount() > 0) {
                    ConsoleHelper.writeMessage(cur.getCurrencyCode() + " - " + cur.getTotalAmount());
                    money = true;
                }
            }
        }
        if (!money) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}