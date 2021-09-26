package com.project.exchange.jobs;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.project.exchange.config.InMemoryCacheConfig;
import io.dropwizard.lifecycle.Managed;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Singleton
public class CurrencyConverterManaged implements Managed {
    private final Long initialDelay = 0L;
    private ExchangeRatesCacheRefresher exchangeRatesCacheRefresher;
    private Long delayPeriod;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @Inject
    public CurrencyConverterManaged(InMemoryCacheConfig inMemoryCacheConfig, ExchangeRatesCacheRefresher exchangeRatesCacheRefresher) {
        this.exchangeRatesCacheRefresher = exchangeRatesCacheRefresher;
        this.delayPeriod = inMemoryCacheConfig.getDelayPeriod();
    }

    @Override
    public void start() throws Exception {
        scheduledExecutorService.scheduleWithFixedDelay(exchangeRatesCacheRefresher, initialDelay, delayPeriod, TimeUnit.SECONDS);
    }

    @Override
    public void stop() throws Exception {
        scheduledExecutorService.shutdown();
        log.info("CurrencyConverterManaged scheduledExecutorService successfully shutdown");
    }
}
