package com.project.exchange.http;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SimpleApacheHttpClient implements IHttpClient {

    @Inject
    private final HttpClient httpClient;

    @Override
    public String executeRequest(CallableRequest request) throws Exception {
        HttpResponse httpResponse = null;
        HttpUriRequest httpUriRequest = null;

        switch (request.getMethod()) {
            case HttpMethod.GET:
                httpUriRequest = doGet(request);
                break;
        }

        try {
            /* execute request */
            httpResponse = httpClient.execute(httpUriRequest);
        }  catch (IOException e) {
            log.error("IO Exception occured while sending http request: ", e);
            return null;
        }

        /* process response */
        if (!Response.Status.Family.SUCCESSFUL.equals(Response.Status.Family.familyOf(httpResponse.getStatusLine().getStatusCode()))) {
            log.error("Non 2XX response received from server for url {} = status code - {} entity - {}",
                    httpUriRequest.getURI(), httpResponse.getStatusLine().getStatusCode(), httpResponse.getEntity());
        }
        return EntityUtils.toString(httpResponse.getEntity());
    }

    private HttpGet doGet(CallableRequest request) throws URISyntaxException {
        /* create the HTTP client and GET request */
        HttpGet httpGet = new HttpGet();
        httpGet.setURI(getRequestUri(request));
        return httpGet;
    }

    private URI getRequestUri(CallableRequest request) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(request.getUrl());
        if (request.getQueryParams() != null) {
            for (Map.Entry<String, String> entry : request.getQueryParams().entrySet()) {
                uriBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        return uriBuilder.build();
    }

}
