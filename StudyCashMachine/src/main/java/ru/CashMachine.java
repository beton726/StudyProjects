package ru;

import ru.output.ConsoleHelper;

public class CashMachine {
    public static void main(String[] args) {
        ConsoleHelper consoleHelper = new ConsoleHelper();

        consoleHelper.getValidTwoDigits(consoleHelper.askCurrencyCode());
    }
}
