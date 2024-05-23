package com.example.companylookup.lookup;

import com.example.companylookup.lookup.dto.request.PostCompaniesSearchRequest;
import com.example.companylookup.lookup.dto.response.PostCompanySearchResponse;
import com.example.companylookup.lookup.repository.CompanySearchRepository;
import com.example.companylookup.lookup.repository.RepositoryManager;
import com.example.companylookup.lookup.repository.entity.CompanySearchEntity;
import com.example.companylookup.lookup.service.TruProxyService;
import com.example.companylookup.lookup.service.dto.TruProxyAPICompanyOfficersPairs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.companylookup.lookup.service.dto.TruProxyAPICompanyOfficersPairs.toCompanySearchResponse;

@Controller
@RequestMapping("search")
public class LookupController {
  private final ObjectMapper objectMapper;

  private final TruProxyService truProxyService;
  private final CompanySearchRepository companySearchRepository;
  private final RepositoryManager repositoryManager;

  public LookupController(
          ObjectMapper objectMapper, TruProxyService truProxyService,
          CompanySearchRepository companySearchRepository,
          RepositoryManager repositoryManager) {
    this.objectMapper = objectMapper;
    this.truProxyService = truProxyService;
    this.companySearchRepository = companySearchRepository;
    this.repositoryManager = repositoryManager;
  }

  @PostMapping("")
  public @ResponseBody PostCompanySearchResponse search(
          @RequestParam(value = "activeOnly", required = false, defaultValue = "false") String activeOnly,
          @RequestHeader("x-api-key") String apiKey,
          @RequestBody PostCompaniesSearchRequest companySearchRequestBody
  ) throws JsonProcessingException {
    if(companySearchRepository.existsById(companySearchRequestBody.getCompanyNumber())) {
      CompanySearchEntity companySearchEntity = companySearchRepository.findById(companySearchRequestBody.getCompanyNumber()).get();

      return objectMapper.readValue(companySearchEntity.getResponse(), PostCompanySearchResponse.class);
    }

    TruProxyAPICompanyOfficersPairs truProxyAPICompanyOfficersPairs =
            truProxyService.searchCompaniesAndOfficers(apiKey, query(companySearchRequestBody), activeOnly);

    PostCompanySearchResponse response = toCompanySearchResponse(truProxyAPICompanyOfficersPairs);

    if (companySearchRequestBody.getCompanyNumber() != null) {
      repositoryManager.saveCompanySearch(companySearchRequestBody.getCompanyNumber(), response);
    }

    return response;
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