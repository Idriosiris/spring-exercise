package com.example.springexample.controller;

import com.example.springexample.api.TruProxyAPI;
import com.example.springexample.controller.request.CompanySearchRequestBody;
import com.example.springexample.controller.response.TruProxyAPICompanyResponse;
import com.example.springexample.controller.response.TruProxyAPIOfficersResponse;
import com.example.springexample.domain.CompanySearchResponse;
import com.example.springexample.domain.TruProxyAPICompanyOfficersPair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.example.springexample.controller.response.TruProxyAPICompanyResponse.toCompanySearchResponse;

@Controller
@RequestMapping("search")
public class SearchController {
    private final TruProxyAPI truProxyAPI;

    public SearchController(
            TruProxyAPI truProxyAPI,
            @Value("${tru-proxy-api.url}") String truProxyApiUrl) {
        this.truProxyAPI = truProxyAPI;
    }

    @PostMapping("/")
    public @ResponseBody CompanySearchResponse search(
           @RequestHeader("x-api-key") String apiKey,
           @RequestBody CompanySearchRequestBody companySearchRequestBody
    ) {
        TruProxyAPICompanyResponse truProxyAPICompanyResponse = truProxyAPI.searchForTruProxyAPICompany(apiKey, getSearchQuery(companySearchRequestBody, companySearchRequestBody));
        TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPair = Arrays.stream(truProxyAPICompanyResponse.getItems()).map(
                trueProxyAPICompany -> {
                    TruProxyAPIOfficersResponse truProxyAPIOfficers = truProxyAPI.getTruProxyAPIOfficers(apiKey, trueProxyAPICompany.getCompanyNumber());

                    return new TruProxyAPICompanyOfficersPair(
                            trueProxyAPICompany,
                            truProxyAPIOfficers.getItems()
                    );
                }
        ).toArray(TruProxyAPICompanyOfficersPair[]::new);

        return toCompanySearchResponse(truProxyAPICompanyOfficersPair, truProxyAPICompanyResponse.getTotalResults());
    }

    private static String getSearchQuery(CompanySearchRequestBody companySearchRequestBody, CompanySearchRequestBody searchRequestBody) {
        if (companySearchRequestBody.getCompanyNumber() != null && companySearchRequestBody.getCompanyNumber() != null) {
            return companySearchRequestBody.getCompanyNumber();
        }

        if (companySearchRequestBody.getCompanyName() != null && companySearchRequestBody.getCompanyNumber() == null) {
            return companySearchRequestBody.getCompanyName();
        }

        return companySearchRequestBody.getCompanyNumber();
    }

}