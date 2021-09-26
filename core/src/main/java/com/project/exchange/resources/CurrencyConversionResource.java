package com.project.exchange.resources;


import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.project.exchange.request.CurrencyConversionRequest;
import com.project.exchange.response.CurrencyConversionResponse;
import com.project.exchange.services.CurrencyConvertorAction;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/currency")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CurrencyConversionResource {

    private final Provider<CurrencyConvertorAction> currencyConvertorActionProvider;

    @GET
    @Path("/convert")
    @Timed
    @ExceptionMetered
    public Response convert(@QueryParam("sourceCurrency") String sourceCurrency,
            @QueryParam("targetCurrency") String targetCurrency,
            @QueryParam("monetaryValue") Double monetaryValue) {
        long timeStart = System.currentTimeMillis();
        CurrencyConversionResponse currencyConversionResponse = currencyConvertorActionProvider.get().withRequest(
                CurrencyConversionRequest.builder()
                        .sourceCurrency(sourceCurrency)
                        .targetCurrency(targetCurrency)
                        .monetaryValue(monetaryValue)
                        .build())
                .invoke();
        long timeTakenInMilliseconds = System.currentTimeMillis() - timeStart;
        return Response.ok()
                .entity(currencyConversionResponse)
                .header("Server-Timing", "total;dur=" + timeTakenInMilliseconds)
                .build();
    }
}
