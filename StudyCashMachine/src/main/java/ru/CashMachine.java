package ru;

import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        String code = ConsoleHelper.askCurrencyCode();
        String[] digits = ConsoleHelper.getValidTwoDigits(code);
        try {
            CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
            cm.addAmount(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
        } catch (NullPointerException e) {
        }
    }
}
