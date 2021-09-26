package com.project.exchange.http;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CallableRequest {
    String url;
    Map<String, String> headers;
    Map<String, String> queryParams;
    Object payload;
    String method;
}
