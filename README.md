# currency-converter-application

A sample Dropwizard based Application which converts currency.

It uses https://exchangeratesapi.io/ APIs in background to fetch exchange data and caches it in memory and computes the rates.

### API Usage - 

```
http://localhost:8080/currency/convert
    ?sourceCurrency=USD
    &targetCurrency=INR
    &monetaryValue=1
```

### Setup
To run the application locally, use below commands - 

Build the jar using maven by running it from the currency-converter-application directory - `mvn clean install`

Start the application using - `java -jar core/target/core-1.0-SNAPSHOT.jar server config.yml`

