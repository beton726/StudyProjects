package ru.output;

import ru.CashMachine;
import ru.Operation;
import ru.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage("Досвидания.");
    }

    public static String readString() throws InterruptOperationException {
        try {
            String readWord = bis.readLine();
            if(readWord.equalsIgnoreCase("exit")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }

            return readWord;
        } catch (IOException ignored) {
        }
        return null;
    }
    // Считаывется код валюты
    public static String askCurrencyCode() throws InterruptOperationException {
        while(true) {
            writeMessage(res.getString("choose.currency.code"));
            String valute = readString();
            if(valute.length() == 3)
                return valute.toUpperCase();
            else
                writeMessage(res.getString("invalid.data"));
        }
    }
    // Считывается номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        while(true) {
            try {
                writeMessage(res.getString("choose.denomination.and.count.format"));
                String[] arr = readString().split(" ");
                if(Integer.parseInt(arr[0]) > 0 && Integer.parseInt(arr[1]) > 0 && arr.length == 2) {
                    return arr;
                } else {
                    writeMessage(res.getString("invalid.data"));
                }
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while(true) {
            try {
                ConsoleHelper.writeMessage(res.getString("choose.operation"));
                ConsoleHelper.writeMessage("\t 1 - " + res.getString("operation.INFO"));
                ConsoleHelper.writeMessage("\t 2 - " + res.getString("operation.DEPOSIT"));
                ConsoleHelper.writeMessage("\t 3 - " + res.getString("operation.WITHDRAW"));
                ConsoleHelper.writeMessage("\t 4 - " + res.getString("operation.EXIT"));
                String operation = readString();
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
    }
}