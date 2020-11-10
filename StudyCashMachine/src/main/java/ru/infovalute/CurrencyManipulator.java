package ru.infovalute;

import ru.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    // Код валюты
    private String currencyCode;
    // Map<номинал,количество>
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<Integer, Integer>(Collections.reverseOrder());
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
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        // Взял готовое решение
        HashMap<Integer, Integer> copyDenominations = new HashMap<Integer, Integer>(denominations);

        ArrayList<Integer> keys = new ArrayList<Integer>(denominations.keySet());

        Collections.sort(keys);
        Collections.reverse(keys);

        TreeMap<Integer, Integer> temporarilyMap = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        for (Integer denomination : keys) {
            int value = copyDenominations.get(denomination);
            while(true) {
                if(expectedAmount < denomination || value == 0) {
                    copyDenominations.put(denomination, value);
                    break;
                }
                expectedAmount -= denomination;
                value--;

                if(temporarilyMap.containsKey(denomination))
                    temporarilyMap.put(denomination, temporarilyMap.get(denomination) + 1);
                else
                    temporarilyMap.put(denomination, 1);
            }
        }
        if (expectedAmount > 0)
            throw new NotEnoughMoneyException();
        else {
            this.denominations.clear();
            this.denominations.putAll(copyDenominations);
        }

        return temporarilyMap;
    }

}