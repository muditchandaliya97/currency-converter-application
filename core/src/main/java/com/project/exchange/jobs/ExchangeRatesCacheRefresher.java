package com.project.exchange.jobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.project.exchange.cache.ExchangeRatesCache;
import com.project.exchange.config.ExchangeRateAPIConfig;
import com.project.exchange.external.ExchangeRateAPIResponse;
import com.project.exchange.http.CallableRequest;
import com.project.exchange.http.IHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.HttpMethod;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class ExchangeRatesCacheRefresher implements Runnable {

    @Inject
    IHttpClient httpClient;
    @Inject
    ExchangeRateAPIConfig exchangeRateAPIConfig;
    @Inject
    ObjectMapper objectMapper;

    @Override
    public void run() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("access_key", exchangeRateAPIConfig.getAccessKey());
        queryParams.put("base", exchangeRateAPIConfig.getBaseCurrency());
        CallableRequest callableRequest = CallableRequest.builder()
                .url(exchangeRateAPIConfig.getUrl())
                .queryParams(queryParams)
                .method(HttpMethod.GET)
                .build();
        ExchangeRateAPIResponse exchangeRateAPIResponse = null;
        try {
            exchangeRateAPIResponse = objectMapper.readValue(httpClient.executeRequest(callableRequest), ExchangeRateAPIResponse.class);
            log.info("Cache Refresh Successful");
        } catch (Exception e) {
            log.error("Cache Refresh failed: {}", e.getMessage());
        }
        if (exchangeRateAPIResponse != null) {
            ExchangeRatesCache.getInstance().updateCache(exchangeRateAPIResponse.getRates());
        }
    }
}
