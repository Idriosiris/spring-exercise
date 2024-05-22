package com.example.springexample.service;

import com.example.springexample.api.TruProxyAPI;
import com.example.springexample.controller.response.TruProxyAPICompanyOfficersPairs;
import com.example.springexample.controller.response.TruProxyAPICompanyResponse;
import com.example.springexample.controller.response.TruProxyAPIOfficersResponse;
import com.example.springexample.domain.TruProxyAPICompanyOfficersPair;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TruProxyService {
  private final TruProxyAPI truProxyAPI;

  public TruProxyService(TruProxyAPI truProxyAPI) {
    this.truProxyAPI = truProxyAPI;
  }

  public TruProxyAPICompanyOfficersPairs searchCompaniesAndOfficers(String apiKey, String query) {
    TruProxyAPICompanyResponse truProxyAPICompanyResponse = truProxyAPI.searchCompanies(apiKey, query);

    return new TruProxyAPICompanyOfficersPairs(Arrays.stream(truProxyAPICompanyResponse.getItems()).map(
            truProxyAPICompany -> {
              TruProxyAPIOfficersResponse truProxyAPIOfficers = truProxyAPI.getOfficers(apiKey, truProxyAPICompany.getCompanyNumber());

              return new TruProxyAPICompanyOfficersPair(
                      truProxyAPICompany,
                      truProxyAPIOfficers.getItems()
              );
            }
    ).toArray(TruProxyAPICompanyOfficersPair[]::new), truProxyAPICompanyResponse.getTotalResults());
  }
}
