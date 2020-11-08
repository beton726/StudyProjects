package  ru.command;

import ru.infovalute.CurrencyManipulator;
import ru.output.ConsoleHelper;

class DepositCommand implements Command {
    public void execute() {

        // Вводим валюту
        String code = ConsoleHelper.askCurrencyCode();
        // Вводим два числа, эти числа возвращаются в массив digits
        String[] digits = ConsoleHelper.getValidTwoDigits(code);
        // Далее эти два числа записываются
        CurrencyManipulator currencyManipulator = new CurrencyManipulator(code);
        currencyManipulator.addAmount(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
        // В объекте currencyManipulator имеется два числа и валюта, которые можно вставить
        // в класс CurrencyManipulatorFactory в Map<String, CurrencyManipulator> map
    }
}