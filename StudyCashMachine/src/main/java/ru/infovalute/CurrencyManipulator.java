package ru.infovalute;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulator {

    // Код валюты
    private String currencyCode;
    // Map<номинал,количество>
    private Map<Integer, Integer> denominations = new HashMap<Integer, Integer>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}