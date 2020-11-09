package ru.infovalute;

import java.util.Collections;
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
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) {
        // Метод возвращает минимальное количество банкнот, которыми набирается запрашиваемая сумма.
        // Если есть несколько вариантов, то использовать тот, в котором максимальное количество
        // банкнот высшего номинала.
        // Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
        Map<Integer,Integer> temporarilyMap = new HashMap<Integer, Integer>();
        // Копируем map
        temporarilyMap.putAll(denominations);


        System.out.println("Ключи: " + temporarilyMap.keySet() + " Значения: " + temporarilyMap.values());

        Integer[] a = temporarilyMap.keySet().toArray(new Integer[0]);  // Купюры с разными номиналами
        int INF = 1000000000;                                           // Значение константы бесконечность
        int[] F = new int[expectedAmount+1];
        int k = a.length;                               // Всего номиналов
        F[0] = 0;

        for(int m = 1; m <= expectedAmount; m++) {      // заполняем массив F
            F[m]=INF;                                   // помечаем, что сумму m выдать нельзя
            for(int i = 0; i < k; i++) {                // перебираем все номиналы банкнот
                if(m >= a[i] && F[m-a[i]]+1 < F[m])
                    F[m] = F[m-a[i]]+1;                 // изменяем значение F[m], если нашли
            }
        }

        int minMoney = F[F.length-1];
        System.out.println("Минимальное количество банкнот: " + minMoney);

        if(F[expectedAmount] == INF) {
            System.out.println("Требуемую сумму выдать невозможно.");
        } else {
            while (expectedAmount > 0) {
                System.out.print("1");
                for (int j : a) {
                    if (F[expectedAmount - j] == F[expectedAmount] - 1) {
                        System.out.println(j);
                        expectedAmount -= j;
                        break;
                    }
                }
            }
        }


        denominations.clear();
        denominations.putAll(temporarilyMap);
        return temporarilyMap;
    }

}