package ru.command;

import ru.exception.InterruptOperationException;
import ru.exception.NotEnoughMoneyException;
import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

import java.util.Map;

class WithdrawCommand implements Command {
    public void execute() throws InterruptOperationException {

        String code = ConsoleHelper.askCurrencyCode();
        // Производится get CurrencyManipulator(Map<номинал,количество>) из словаря по определённой валюте
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        boolean flag = true;
        while(flag) {
            ConsoleHelper.writeMessage("Введите сумму.");

            try {
                String amountWithdraw = ConsoleHelper.readString();

                if(currencyManipulator.isAmountAvailable(Integer.parseInt(amountWithdraw))) {
                    flag = false;
                    Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(Integer.parseInt(amountWithdraw));

                    for (Integer item : denominations.keySet()) {
                        ConsoleHelper.writeMessage("\t" + item + " - " + denominations.get(item));
                    }

                    ConsoleHelper.writeMessage(String.format("%d %s was withdrawn successfully", amountWithdraw, code));
                } else {
                    ConsoleHelper.writeMessage("Недостаточно денег для выдачи.");
                }

            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Введены не корректные данные.");
            } catch (NotEnoughMoneyException e) {
                e.printStackTrace();
            }
        }
    }
}