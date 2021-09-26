package com.project.exchange.exceptions;

import lombok.Getter;
import org.apache.http.HttpStatus;

public enum CurrencyConverterExceptionType {
    UnsupportedCurrencySymbol(HttpStatus.SC_BAD_REQUEST, "Unsupported Currency Symbol");

    @Getter
    private int httpStatusCode;
    @Getter
    private String reason;

    CurrencyConverterExceptionType(int httpStatusCode, String reason) {
        this.httpStatusCode = httpStatusCode;
        this.reason = reason;
    }
}
