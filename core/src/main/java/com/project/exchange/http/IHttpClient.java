package com.project.exchange.http;

public interface IHttpClient {
    String executeRequest(CallableRequest request) throws Exception;
}
