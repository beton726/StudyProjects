package ru.command;

import ru.CashMachine;
import ru.exception.InterruptOperationException;
import ru.output.ConsoleHelper;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards =  ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Введите номер карты 12 цифр и пин 4 цифры.");

            String cardNumberEnter = ConsoleHelper.readString();
            String pinEnter = ConsoleHelper.readString();
            if(cardNumberEnter.length() != 12 || pinEnter.length() != 4)
                ConsoleHelper.writeMessage("Введены не корректные данные.");
            if(validCreditCards.containsKey(cardNumberEnter) && pinEnter.equals(validCreditCards.getString(cardNumberEnter))) {
                ConsoleHelper.writeMessage("Верификация прошла успешно.");
                break;
            }

        }
    }
}