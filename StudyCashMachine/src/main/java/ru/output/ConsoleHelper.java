package ru.output;

import ru.Operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            String readWord = bis.readLine();
            return readWord;
        } catch (IOException e) {
        }
        return null;
    }
    // Считаывется код валюты
    public static String askCurrencyCode() {
        while(true) {
            writeMessage("Введите валюту.");
            String valute = readString();
            if(valute.length() == 3)
                return valute.toUpperCase();
            else
                writeMessage("Введены не корректные данные.");
        }
    }
    // Считывается номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) {
        while(true) {
            try {
                writeMessage("Введите два целых числа.");
                String[] arr = readString().split(" ");
                if(Integer.parseInt(arr[0]) > 0 && Integer.parseInt(arr[1]) > 0 && arr.length == 2) {
                    return arr;
                } else {
                    writeMessage("Введены не корректные данные.");
                }
            } catch (Exception e) {
                writeMessage("Введены не корректные данные.");
                continue;
            }
        }
    }

    public static Operation askOperation() {
        while(true) {
            try {
                writeMessage("Какую операцию хотите ввести?");
                String operation = readString();
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
            } catch (IllegalArgumentException e) {
                writeMessage("Введены не корректные данные. Попробуйте ещё раз.");
                continue;
            }
        }
    }
}