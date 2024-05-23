package com.example.companylookup.lookup.service.dto;

import com.example.companylookup.lookup.api.dto.TruProxyAPICompany;
import com.example.companylookup.lookup.api.dto.TruProxyAPIOfficer;
import com.example.companylookup.lookup.dto.response.Address;
import com.example.companylookup.lookup.dto.response.Company;
import com.example.companylookup.lookup.dto.response.Officer;
import com.example.companylookup.lookup.dto.response.PostCompanySearchResponse;

import java.util.Arrays;

public class TruProxyAPICompanyOfficersPairs {
  private TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs;
  private int totalResults;

  public TruProxyAPICompanyOfficersPairs(TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs, int totalResults) {
    this.truProxyAPICompanyOfficersPairs = truProxyAPICompanyOfficersPairs;
    this.totalResults = totalResults;
  }

  public TruProxyAPICompanyOfficersPair[] getTruProxyAPICompanyOfficersPairs() {
    return truProxyAPICompanyOfficersPairs;
  }

  public void setTruProxyAPICompanyOfficersPairs(TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs) {
    this.truProxyAPICompanyOfficersPairs = truProxyAPICompanyOfficersPairs;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }

  public static PostCompanySearchResponse toCompanySearchResponse(TruProxyAPICompanyOfficersPairs truProxyAPICompanyOfficersPairs) {
    Company[] companies = Arrays.stream(truProxyAPICompanyOfficersPairs.getTruProxyAPICompanyOfficersPairs()).map(
            truProxyAPICompanyOfficersPair -> {
              TruProxyAPICompany company = truProxyAPICompanyOfficersPair.getCompany();
              TruProxyAPIOfficer[] officers = truProxyAPICompanyOfficersPair.getOfficers();

              return new Company(
                      company.getCompanyNumber(),
                      company.getCompanyType(),
                      company.getTitle(),
                      company.getCompanyStatus(),
                      company.getDateOfCreation(),
                      new Address(
                              company.getAddress().getLocality(),
                              company.getAddress().getPostalCode(),
                              company.getAddress().getPremises(),
                              company.getAddress().getAddressLine1(),
                              company.getAddress().getCountry()
                      ),
                      Arrays.stream(officers != null ? officers : new TruProxyAPIOfficer[]{}).map(
                              truProxyAPIOfficer -> new Officer(
                                      truProxyAPIOfficer.getName(),
                                      truProxyAPIOfficer.getOfficerRole(),
                                      truProxyAPIOfficer.getAppointedOn(),
                                      new Address(
                                              truProxyAPIOfficer.getAddress().getPremises(),
                                              truProxyAPIOfficer.getAddress().getLocality(),
                                              truProxyAPIOfficer.getAddress().getAddressLine1(),
                                              truProxyAPIOfficer.getAddress().getCountry(),
                                              truProxyAPIOfficer.getAddress().getPostalCode()
                                      )
                              )
                      ).toArray(Officer[]::new)
              );
            }
    ).toArray(Company[]::new);

    return new PostCompanySearchResponse(companies, truProxyAPICompanyOfficersPairs.getTotalResults());
  }
}
