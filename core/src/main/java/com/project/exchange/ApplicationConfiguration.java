package com.project.exchange;

import com.project.exchange.config.ExchangeRateAPIConfig;
import com.project.exchange.config.InMemoryCacheConfig;
import io.dropwizard.Configuration;
import lombok.Getter;

@Getter
public class ApplicationConfiguration extends Configuration {
    public ExchangeRateAPIConfig exchangeRateAPIConfig;

    public InMemoryCacheConfig inMemoryCacheConfig;
}
