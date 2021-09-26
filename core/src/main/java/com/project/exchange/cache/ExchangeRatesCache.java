package com.project.exchange.cache;

import com.project.exchange.exceptions.CurrencyConverterExceptionType;
import com.project.exchange.exceptions.CurrencyConverterRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExchangeRatesCache {

    private static final ExchangeRatesCache INSTANCE = new ExchangeRatesCache();

    private Map<String, Double> exchangeRates = new HashMap<>();

    public static ExchangeRatesCache getInstance() {
        return INSTANCE;
    }

    public synchronized void updateCache(Map<String, Double> rates) {
        INSTANCE.exchangeRates.putAll(rates);
    }

    public Double getExchangeRate(String symbol) throws CurrencyConverterRuntimeException {
        if (isValidSymbol(symbol)) {
            return INSTANCE.exchangeRates.get(symbol);
        }
        throw new CurrencyConverterRuntimeException(CurrencyConverterExceptionType.UnsupportedCurrencySymbol);
    }

    private boolean isValidSymbol(String symbol) {
        return INSTANCE.exchangeRates.containsKey(symbol);
    }
}
