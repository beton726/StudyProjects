package  ru.command;

import ru.CashMachine;
import ru.exception.InterruptOperationException;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en", Locale.ENGLISH);

    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        // Вводим валюту
        String code = ConsoleHelper.askCurrencyCode();
        try {
            // Вводим два числа, эти числа возвращаются в массив digits
            String[] digits = ConsoleHelper.getValidTwoDigits(code);
            // Далее эти два числа записываются
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code).addAmount(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(digits[0]) * Integer.parseInt(digits[1]), code));
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}