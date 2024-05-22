package com.example.springexample.api;

import com.example.springexample.controller.response.TruProxyAPICompanyResponse;
import com.example.springexample.controller.response.TruProxyAPIOfficersResponse;
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

  public TruProxyAPICompanyResponse searchCompanies(String apiKey, String query) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-api-key", apiKey);

    URI uri = UriComponentsBuilder.fromUriString(truProxyApiUrl + "/Search")
            .queryParam("Query", query)
            .buildAndExpand(query)
            .toUri();

    return restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            TruProxyAPICompanyResponse.class).getBody();
  }

  public TruProxyAPIOfficersResponse getOfficers(String apiKey, String companyNumber) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-api-key", apiKey);

    URI uri = UriComponentsBuilder.fromUriString(truProxyApiUrl + "/Officers")
            .queryParam("CompanyNumber", companyNumber)
            .buildAndExpand(companyNumber)
            .toUri();

    return restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            TruProxyAPIOfficersResponse.class).getBody();
  }
}
