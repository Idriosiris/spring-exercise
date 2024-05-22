package com.example.springexample.controller;

import com.example.springexample.controller.request.CompaniesSearchRequestBody;
import com.example.springexample.controller.response.TruProxyAPICompanyOfficersPairs;
import com.example.springexample.domain.CompanySearchResponse;
import com.example.springexample.service.TruProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.springexample.controller.response.TruProxyAPICompanyResponse.toCompanySearchResponse;

@Controller
@RequestMapping("search")
public class SearchController {
  private final TruProxyService truProxyService;

  public SearchController(
          TruProxyService truProxyService) {
    this.truProxyService = truProxyService;
  }

  @PostMapping("/")
  public @ResponseBody CompanySearchResponse search(
          @RequestHeader("x-api-key") String apiKey,
          @RequestBody CompaniesSearchRequestBody companySearchRequestBody
  ) {
    TruProxyAPICompanyOfficersPairs truProxyAPICompanyOfficersPairs = truProxyService.searchCompaniesAndOfficers(apiKey, query(companySearchRequestBody));

    return toCompanySearchResponse(truProxyAPICompanyOfficersPairs);
  }

  private static String query(CompaniesSearchRequestBody companySearchRequestBody) {
    if (companySearchRequestBody.getCompanyNumber() != null && companySearchRequestBody.getCompanyNumber() != null) {
      return companySearchRequestBody.getCompanyNumber();
    }

    if (companySearchRequestBody.getCompanyName() != null && companySearchRequestBody.getCompanyNumber() == null) {
      return companySearchRequestBody.getCompanyName();
    }

    return companySearchRequestBody.getCompanyNumber();
  }

}