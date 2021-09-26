package com.project.exchange.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.project.exchange.ApplicationConfiguration;
import com.project.exchange.config.ExchangeRateAPIConfig;
import com.project.exchange.config.InMemoryCacheConfig;
import com.project.exchange.http.IHttpClient;
import com.project.exchange.http.SimpleApacheHttpClient;
import com.project.exchange.resources.CurrencyConversionResource;
import com.project.exchange.resources.HealthCheckResource;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class CurrencyConverterModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        bindResources();
        bindImplementations();
    }

    private void bindResources() {
        bind(HealthCheckResource.class).in(Singleton.class);
        bind(CurrencyConversionResource.class).in(Singleton.class);
    }

    private void bindImplementations() {
        bind(IHttpClient.class).to(SimpleApacheHttpClient.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public ExchangeRateAPIConfig providesExchangeRateAPIConfig(Provider<ApplicationConfiguration> applicationConfiguration) {
        ExchangeRateAPIConfig x = applicationConfiguration.get().getExchangeRateAPIConfig();
        return applicationConfiguration.get().getExchangeRateAPIConfig();
    }

    @Provides
    @Singleton
    public HttpClient providesHttpClient(Provider<ApplicationConfiguration> applicationConfiguration) {
        return HttpClientBuilder.create().build();
    }

    @Provides
    @Singleton
    public InMemoryCacheConfig providesInMemoryCacheConfig(Provider<ApplicationConfiguration> applicationConfiguration) {
        return applicationConfiguration.get().getInMemoryCacheConfig();
    }
}
