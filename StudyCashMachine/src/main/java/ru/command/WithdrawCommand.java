package ru.command;

import ru.exception.InterruptOperationException;
import ru.infovalute.CurrencyManipulator;
import ru.infovalute.CurrencyManipulatorFactory;
import ru.output.ConsoleHelper;

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
                    currencyManipulator.withdrawAmount(Integer.parseInt(amountWithdraw));

                } else {
                    ConsoleHelper.writeMessage("Недостаточно денег для выдачи.");
                }


            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Введены не корректные данные.");
            }


        }

    }
}