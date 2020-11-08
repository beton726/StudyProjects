package  ru.command;

import ru.exception.InterruptOperationException;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

class DepositCommand implements Command {
    public void execute() throws InterruptOperationException {
        // Вводим валюту
        String code = ConsoleHelper.askCurrencyCode();
        // Вводим два числа, эти числа возвращаются в массив digits
        String[] digits = ConsoleHelper.getValidTwoDigits(code);
        // Далее эти два числа записываются
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code).addAmount(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
    }
}