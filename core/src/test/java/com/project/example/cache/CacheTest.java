package com.project.example.cache;

import com.project.exchange.cache.ExchangeRatesCache;
import com.project.exchange.exceptions.CurrencyConverterRuntimeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class CacheTest {

    @Before
    public void Setup() {
        ExchangeRatesCache.getInstance().updateCache(Collections.singletonMap("EUR", 1D));
    }

    @Test
    public void testGetExchangeRate() {
        Double rate = ExchangeRatesCache.getInstance().getExchangeRate("EUR");
        Assert.assertEquals(rate, Double.valueOf(1D));
    }

    @Test(expected = CurrencyConverterRuntimeException.class)
    public void testInvalidCurrencySymbol() {
        ExchangeRatesCache.getInstance().getExchangeRate("USD");
    }
}
