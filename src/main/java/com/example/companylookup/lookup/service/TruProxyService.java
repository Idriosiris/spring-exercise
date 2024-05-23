package com.example.companylookup.lookup.service;

import com.example.companylookup.lookup.api.TruProxyAPI;
import com.example.companylookup.lookup.service.dto.TruProxyAPICompanyOfficersPairs;
import com.example.companylookup.lookup.api.dto.TruProxyAPICompanyResponse;
import com.example.companylookup.lookup.api.dto.TruProxyAPIOfficer;
import com.example.companylookup.lookup.api.dto.TruProxyAPIOfficersResponse;
import com.example.companylookup.lookup.service.dto.TruProxyAPICompanyOfficersPair;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TruProxyService {
  private final TruProxyAPI truProxyAPI;

  public TruProxyService(TruProxyAPI truProxyAPI) {
    this.truProxyAPI = truProxyAPI;
  }

  public TruProxyAPICompanyOfficersPairs searchCompaniesAndOfficers(String apiKey, String query, String activeOnly) {
    TruProxyAPICompanyResponse truProxyAPICompanyResponse = truProxyAPI.searchCompanies(apiKey, query);

    return new TruProxyAPICompanyOfficersPairs(
            Arrays.stream(truProxyAPICompanyResponse.getItems())
                    .filter(truProxyAPICompany -> !activeOnly.equals("true") || truProxyAPICompany.getCompanyStatus().equals("active"))
                    .map(
                            truProxyAPICompany -> {
                              TruProxyAPIOfficersResponse truProxyAPIOfficers = truProxyAPI.getOfficers(apiKey, truProxyAPICompany.getCompanyNumber());

                              TruProxyAPIOfficer[] officers = truProxyAPIOfficers.getItems();

                              TruProxyAPIOfficer[] activeOfficers = Arrays.stream(officers != null ? officers : new TruProxyAPIOfficer[]{})
                                      .filter(truProxyAPIOfficer -> truProxyAPIOfficer.getResignedOn() != null)
                                      .toArray(TruProxyAPIOfficer[]::new);

                              return new TruProxyAPICompanyOfficersPair(
                                      truProxyAPICompany,
                                      activeOfficers);
                            }).toArray(TruProxyAPICompanyOfficersPair[]::new),
            truProxyAPICompanyResponse.getTotalResults());
  }
}
