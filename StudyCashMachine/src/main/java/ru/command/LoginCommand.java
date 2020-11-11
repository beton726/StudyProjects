package ru.command;

import ru.CashMachine;
import ru.exception.InterruptOperationException;
import ru.output.ConsoleHelper;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards =  ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".resources.login_en", Locale.ENGLISH);

    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));

            String cardNumberEnter = ConsoleHelper.readString();
            String pinEnter = ConsoleHelper.readString();
            if(cardNumberEnter.length() != 12 || pinEnter.length() != 4)
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            try {
                if(validCreditCards.containsKey(cardNumberEnter) && pinEnter.equals(validCreditCards.getString(cardNumberEnter))) {
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }


        }
    }
}