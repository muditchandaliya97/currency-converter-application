package com.project.exchange.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InMemoryCacheConfig {
    Long delayPeriod = 86400L; //In seconds. Default value is = 1 day;
}
