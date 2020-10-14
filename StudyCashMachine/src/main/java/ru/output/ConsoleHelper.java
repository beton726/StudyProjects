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
    public String askCurrencyCode() {
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
    public String [] getValidTwoDigits(String currencyCode) {
        System.out.println("Введите два целых числа.");
//        String par = readString(); // Номинал
//        String countMoney = readString(); // Количество банкнот
        String[] numb = readString().split(" ");
        // Как то обработать ввод чисел
        System.out.println(numb[0] + " " + numb[1]);
        return null;
    }
}