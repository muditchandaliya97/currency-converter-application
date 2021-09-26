package com.project.example.services;

import com.project.exchange.cache.ExchangeRatesCache;
import com.project.exchange.exceptions.CurrencyConverterRuntimeException;
import com.project.exchange.request.CurrencyConversionRequest;
import com.project.exchange.response.CurrencyConversionResponse;
import com.project.exchange.services.CurrencyConvertorAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExchangeRatesCache.class)
public class CurrencyConverterActionTest {
    CurrencyConvertorAction currencyConvertorAction;

    @Mock
    ExchangeRatesCache exchangeRatesCache;

    @Before
    public void setUp() {
        currencyConvertorAction = new CurrencyConvertorAction();
        PowerMockito.mockStatic(ExchangeRatesCache.class);
        when(ExchangeRatesCache.getInstance()).thenReturn(exchangeRatesCache);
        when(ExchangeRatesCache.getInstance().getExchangeRate("USD")).thenReturn(1.17);
        when(ExchangeRatesCache.getInstance().getExchangeRate("INR")).thenReturn(86.79);
    }

    @Test
    public void testCurrencyConversion() {
        CurrencyConversionRequest currencyConversionRequest = CurrencyConversionRequest.builder()
                .sourceCurrency("USD")
                .targetCurrency("INR")
                .monetaryValue(1D)
                .build();

        Double convertedValue = currencyConvertorAction.withRequest(currencyConversionRequest).invoke().getConvertedValue();
        Assert.assertEquals(convertedValue, Double.valueOf(74.1794871794872));
    }
}
