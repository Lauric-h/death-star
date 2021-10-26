package com.springmsa.deathstar.httprequest;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class RequestBuilder {

    public static String buildUri(String host, String path) {
        // construct a request
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host(host).path(path).build();

        System.out.println(uriComponents);

        return uriComponents.toUriString();
    }

    public static String buildUriWithParam(String uri, String paramName, Object param) {
        return UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam(paramName, param)
                .encode()
                .toUriString();
    }
}
