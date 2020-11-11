package ru.command;

import ru.exception.InterruptOperationException;
import ru.output.ConsoleHelper;

public class LoginCommand implements Command {

    private static final long cardNumber = 123456789012L;
    private static final int pin = 1234;

    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Введите номер карты 12 цифр и пин 4 цифры.");
            String cardNumberEnter = ConsoleHelper.readString();
            String pinEnter = ConsoleHelper.readString();
            if(cardNumberEnter.length() != 12 || pinEnter.length() != 4)
                ConsoleHelper.writeMessage("Введены не корректные данные.");
            if(cardNumberEnter.equals(cardNumber) && pinEnter.equals(pin)) {
                ConsoleHelper.writeMessage("Верификация прошла успешно.");

            }





        }
    }

}