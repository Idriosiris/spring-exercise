package com.example.springexample.controller;

import com.example.springexample.controller.response.TrueProxyAPICompanyResponse;
import com.example.springexample.domain.Address;
import com.example.springexample.domain.Company;
import com.example.springexample.domain.CompanySearchResponse;
import com.example.springexample.domain.Officer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@RequestMapping("search")
public class SearchController {

    private final RestTemplate restTemplate;
    private final String truProxyApiUrl;

    public SearchController(RestTemplate restTemplate, @Value("${tru-proxy-api.url}") String truProxyApiUrl) {
        this.restTemplate = restTemplate;
        this.truProxyApiUrl = truProxyApiUrl;
    }

    @PostMapping("/")
    public @ResponseBody CompanySearchResponse search() {
        TrueProxyAPICompanyResponse trueProxyAPICompanyResponse = searchForTrueProxyAPICompany();

        return toCompanySearchResponse(trueProxyAPICompanyResponse);
    }

    private static CompanySearchResponse toCompanySearchResponse(TrueProxyAPICompanyResponse trueProxyAPICompanyResponse) {
        Company[] companies = Arrays.stream(trueProxyAPICompanyResponse.getItems()).map(
                trueProxyAPICompany -> new Company(
                        trueProxyAPICompany.getCompanyNumber(),
                        trueProxyAPICompany.getCompanyType(),
                        trueProxyAPICompany.getTitle(),
                        trueProxyAPICompany.getCompanyStatus(),
                        trueProxyAPICompany.getDateOfCreation(),
                        new Address(
                                trueProxyAPICompany.getAddress().getLocality(),
                                trueProxyAPICompany.getAddress().getPostalCode(),
                                trueProxyAPICompany.getAddress().getPremises(),
                                trueProxyAPICompany.getAddress().getAddressLine1(),
                                trueProxyAPICompany.getAddress().getCountry()
                        ),
                        new Officer[]{
                                new Officer(
                                        "BOXALL, Sarah Victoria",
                                        "secretary",
                                        "2008-02-11",
                                        new Address(
                                                "5",
                                                "London",
                                                "Cranford Close",
                                                "England",
                                                "SW20 0DP"
                                        )
                                )
                        }
                )).toArray(Company[]::new);

        return new CompanySearchResponse(companies, trueProxyAPICompanyResponse.getTotalResults());
    }

    public TrueProxyAPICompanyResponse searchForTrueProxyAPICompany() {
        return this.restTemplate.getForObject(
                truProxyApiUrl + "/Search?Query={searchTerm}",
                TrueProxyAPICompanyResponse.class,
                "BBC");
    }

}