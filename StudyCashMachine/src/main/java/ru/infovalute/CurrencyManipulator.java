package ru.infovalute;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {

    // Код валюты
    private String currencyCode;
    // Map<номинал,количество>
    private static Map<Integer, Integer> denominations = new HashMap<Integer, Integer>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    // Добавление введённых номинала(denomination) и количество банкнот(count)
    public void addAmount(int denomination, int count) {
        if(denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }
    // Подсчёт общей суммы денег для выбранной валюты
    public int getTotalAmount() {
        int countCash = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            countCash += entry.getKey() * entry.getValue();
        }
        return countCash;
    }

    public static boolean hasMoney() {

        return true;
    }

}