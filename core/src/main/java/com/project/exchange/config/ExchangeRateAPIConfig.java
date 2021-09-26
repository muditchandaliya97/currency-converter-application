package com.project.exchange.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeRateAPIConfig {
    String url;
    String accessKey;
    String baseCurrency;
}
