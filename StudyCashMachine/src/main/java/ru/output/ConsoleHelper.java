package ru.output;

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
            System.out.println("Введите валюту.");
            String valute = readString();
            if(valute.length() == 3)
                return valute.toUpperCase();
            else
                System.out.println("Введены не корректные данные.");
        }
    }
    // Считывается номинал и количество банкнот
    public static String[] getValidTwoDigits(String currencyCode) {
            while(true) {
                try {
                    System.out.println("Введите два целых числа.");
                    String[] arr = readString().split(" ");
                    if(Integer.parseInt(arr[0]) > 0 && Integer.parseInt(arr[1]) > 0 && arr.length == 2) {
                        return arr;
                    } else {
                        System.out.println("Введены не корректные данные.");
                    }
                } catch (Exception e) {
                    System.out.println("Введены не корректные данные.");
                    continue;
                }
            }
    }
}