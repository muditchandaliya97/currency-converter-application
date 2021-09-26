package com.project.exchange.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ApplicationExceptionMapper implements ExceptionMapper<CurrencyConverterRuntimeException> {
    @Override
    public Response toResponse(CurrencyConverterRuntimeException e) {
        return Response.status(e.getCurrencyConverterExceptionType().getHttpStatusCode())
                .entity(e.getCurrencyConverterExceptionType().getReason())
                .build();
    }
}
