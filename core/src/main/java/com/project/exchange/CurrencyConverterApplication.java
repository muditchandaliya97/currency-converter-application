package com.project.exchange;

import com.project.exchange.exceptions.ApplicationExceptionMapper;
import com.project.exchange.guice.CurrencyConverterModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CurrencyConverterApplication extends Application<ApplicationConfiguration> {

    GuiceBundle guiceBundle;

    public static void main(String[] args) throws Exception {
        new CurrencyConverterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        super.initialize(bootstrap);
        guiceBundle = GuiceBundle.builder()
                .modules(new CurrencyConverterModule())
                .enableAutoConfig("com.project.exchange")
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(ApplicationConfiguration applicationConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new ApplicationExceptionMapper());
    }
}
