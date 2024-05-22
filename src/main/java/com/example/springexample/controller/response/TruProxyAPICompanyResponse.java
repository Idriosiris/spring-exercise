package com.example.springexample.controller.response;

import com.example.springexample.domain.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class TruProxyAPICompanyResponse {
    @JsonProperty("page_number")
    private int pageNumber;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("items")
    private TrueProxyAPICompany[] items;

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

    public TrueProxyAPICompany[] getItems() {
        return items;
    }

    public void setItems(TrueProxyAPICompany[] items) {
        this.items = items;
    }

    /*
     * TODO this transformer needs to be inside TruProxyAPICompanyOfficersPair or somewhere else
     * */
    public static CompanySearchResponse toCompanySearchResponse(TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs, int totalResults) {
        Company[] companies = Arrays.stream(truProxyAPICompanyOfficersPairs).map(
                truProxyAPICompanyOfficersPair -> {
                    TrueProxyAPICompany company = truProxyAPICompanyOfficersPair.getCompany();
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
                                    trueProxyAPIOfficer -> new Officer(
                                            trueProxyAPIOfficer.getName(),
                                            trueProxyAPIOfficer.getOfficerRole(),
                                            trueProxyAPIOfficer.getAppointedOn(),
                                            new Address(
                                                    trueProxyAPIOfficer.getAddress().getPremises(),
                                                    trueProxyAPIOfficer.getAddress().getLocality(),
                                                    trueProxyAPIOfficer.getAddress().getAddressLine1(),
                                                    trueProxyAPIOfficer.getAddress().getCountry(),
                                                    trueProxyAPIOfficer.getAddress().getPostalCode()
                                            )
                                    )
                            ).toArray(Officer[]::new)
                    );
                }
        ).toArray(Company[]::new);

        return new CompanySearchResponse(companies, totalResults);
    }
}
