package ru;

import ru.command.CommandExecutor;
import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation operation;
        do {
            // DEPOSIT
            operation = ConsoleHelper.askOperation(); // Вводим номер операции
            if(operation == Operation.EXIT)
                break;
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);

    }
}