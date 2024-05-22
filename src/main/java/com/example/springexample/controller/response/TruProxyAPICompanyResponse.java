package com.example.springexample.controller.response;

import com.example.springexample.domain.Address;
import com.example.springexample.domain.Company;
import com.example.springexample.domain.CompanySearchResponse;
import com.example.springexample.domain.Officer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TruProxyAPICompanyResponse {
  @JsonProperty("page_number")
  private int pageNumber;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("total_results")
  private int totalResults;

  @JsonProperty("items")
  private TruProxyAPICompany[] items;

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }

  public TruProxyAPICompany[] getItems() {
    return items;
  }

  public void setItems(TruProxyAPICompany[] items) {
    this.items = items;
  }

  /*
   * TODO this transformer needs to be inside TruProxyAPICompanyOfficersPair or somewhere else
   * */
  public static CompanySearchResponse toCompanySearchResponse(TruProxyAPICompanyOfficersPairs truProxyAPICompanyOfficersPairs) {
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

    return new CompanySearchResponse(companies, truProxyAPICompanyOfficersPairs.getTotalResults());
  }
}
