package com.example.springexample.api;

import com.example.springexample.controller.response.TruProxyAPICompanyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TruProxyAPI {
    private final RestTemplate restTemplate;
    private final String truProxyApiUrl;

    public TruProxyAPI(RestTemplate restTemplate, @Value("${tru-proxy-api.url}") String truProxyApiUrl) {
        this.restTemplate = restTemplate;
        this.truProxyApiUrl = truProxyApiUrl;
    }

    public TruProxyAPICompanyResponse searchForTruProxyAPICompany(String apiKey, String searchQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        URI uri = UriComponentsBuilder.fromUriString(truProxyApiUrl + "/Search")
                .queryParam("Query", searchQuery)
                .buildAndExpand(searchQuery)
                .toUri();

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                TruProxyAPICompanyResponse.class).getBody();
    }
}
