package com.project.exchange.exceptions;

import lombok.Getter;

public class CurrencyConverterRuntimeException extends RuntimeException {
    public CurrencyConverterRuntimeException(CurrencyConverterExceptionType currencyConverterExceptionType) {
        this.currencyConverterExceptionType = currencyConverterExceptionType;
    }

    @Getter
    private CurrencyConverterExceptionType currencyConverterExceptionType;
}
