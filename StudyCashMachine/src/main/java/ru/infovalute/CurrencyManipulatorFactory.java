package ru.infovalute;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {

    private static Map<String, CurrencyManipulator> map = new HashMap<String, CurrencyManipulator>();

    private CurrencyManipulatorFactory(){
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        return map.get(currencyCode.toUpperCase());
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }

    public static boolean hasMoney() {
        if(map.isEmpty())
            return false;
        // Возвращает true, если общая сумма данной валюты > 0




        return true;
    }

}