package com.example.companylookup.lookup;

import com.example.companylookup.lookup.dto.request.PostCompaniesSearchRequest;
import com.example.companylookup.lookup.service.dto.TruProxyAPICompanyOfficersPairs;
import com.example.companylookup.lookup.dto.response.PostCompanySearchResponse;
import com.example.companylookup.lookup.service.TruProxyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.companylookup.lookup.service.dto.TruProxyAPICompanyOfficersPairs.toCompanySearchResponse;

@Controller
@RequestMapping("search")
public class LookupController {
  private final TruProxyService truProxyService;

  public LookupController(
          TruProxyService truProxyService) {
    this.truProxyService = truProxyService;
  }

  @PostMapping("")
  public @ResponseBody PostCompanySearchResponse search(
          @RequestParam(value = "activeOnly", required = false, defaultValue = "false") String activeOnly,
          @RequestHeader("x-api-key") String apiKey,
          @RequestBody PostCompaniesSearchRequest companySearchRequestBody
  ) {
    TruProxyAPICompanyOfficersPairs truProxyAPICompanyOfficersPairs =
            truProxyService.searchCompaniesAndOfficers(apiKey, query(companySearchRequestBody), activeOnly);

    return toCompanySearchResponse(truProxyAPICompanyOfficersPairs);
  }

  private static String query(PostCompaniesSearchRequest companySearchRequestBody) {
    if (companySearchRequestBody.getCompanyNumber() != null && companySearchRequestBody.getCompanyNumber() != null) {
      return companySearchRequestBody.getCompanyNumber();
    }

    if (companySearchRequestBody.getCompanyName() != null && companySearchRequestBody.getCompanyNumber() == null) {
      return companySearchRequestBody.getCompanyName();
    }

    return companySearchRequestBody.getCompanyNumber();
  }

}