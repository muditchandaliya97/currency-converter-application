package com.project.exchange.services;

import com.project.exchange.cache.ExchangeRatesCache;
import com.project.exchange.request.CurrencyConversionRequest;
import com.project.exchange.response.CurrencyConversionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrencyConvertorAction implements Action<CurrencyConversionResponse> {
    CurrencyConversionRequest request;

    @Override
    public CurrencyConversionResponse invoke() {
        return CurrencyConversionResponse.builder()
                .convertedValue(getConvertedCurrency())
                .build();
    }

    public CurrencyConvertorAction withRequest(CurrencyConversionRequest request) {
        this.request = request;
        return this;
    }

    private Double getConvertedCurrency() {
        Double exchangeRateOfSourceToBase = ExchangeRatesCache.getInstance().getExchangeRate(request.getSourceCurrency());
        Double exchangeRateOfTargetToBase = ExchangeRatesCache.getInstance().getExchangeRate(request.getTargetCurrency());
        Double monetaryValueInBaseCurrency = request.getMonetaryValue() / exchangeRateOfSourceToBase;
        Double monetaryValueInTargetCurrency = monetaryValueInBaseCurrency * exchangeRateOfTargetToBase;
        return monetaryValueInTargetCurrency;
    }

}
