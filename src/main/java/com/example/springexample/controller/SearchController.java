package com.example.springexample.controller;

import com.example.springexample.api.TruProxyAPI;
import com.example.springexample.controller.request.CompanySearchRequestBody;
import com.example.springexample.controller.response.TruProxyAPICompanyResponse;
import com.example.springexample.domain.CompanySearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        TruProxyAPICompanyResponse truProxyAPICompanyResponse = truProxyAPI.searchForTruProxyAPICompany(apiKey, companySearchRequestBody);

        return toCompanySearchResponse(truProxyAPICompanyResponse);
    }

}