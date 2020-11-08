package ru.infovalute;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {

    // Код валюты
    private String currencyCode;
    // Map<номинал,количество>
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<Integer, Integer>();
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

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }
    // Проверка, достаточно ли средств.
    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }
    // Списание со счёта
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) {
        // Метод возвращает минимальное количество банкнот, которыми набирается запрашиваемая сумма.
        // Если есть несколько вариантов, то использовать тот, в котором максимальное количество
        // банкнот высшего номинала.
        // Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
        Map<Integer,Integer> temporarilyMap = new HashMap<Integer, Integer>();
        // Копируем map
        temporarilyMap.putAll(denominations);

        // Делаем манипуляции





        denominations.clear();
        denominations.putAll(temporarilyMap);
        return temporarilyMap;
    }

}