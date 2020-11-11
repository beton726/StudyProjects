package ru.command;

import ru.CashMachine;
import ru.exception.InterruptOperationException;
import ru.exception.NotEnoughMoneyException;
import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.withdraw_en", Locale.ENGLISH);

    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String code = ConsoleHelper.askCurrencyCode();
        // Производится get CurrencyManipulator(Map<номинал,количество>) из словаря по определённой валюте
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        boolean flag = true;
        while(flag) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));

            try {
                String amountWithdraw = ConsoleHelper.readString();

                if(currencyManipulator.isAmountAvailable(Integer.parseInt(amountWithdraw))) {
                    flag = false;
                    Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(Integer.parseInt(amountWithdraw));

                    for (Integer item : denominations.keySet()) {
                        ConsoleHelper.writeMessage("\t" + item + " - " + denominations.get(item));
                    }

                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amountWithdraw, code));
                } else {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }

            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            } catch (NotEnoughMoneyException e) {
                e.printStackTrace();
            }
        }
    }
}