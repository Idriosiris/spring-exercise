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

    public static CompanySearchResponse toCompanySearchResponse(TruProxyAPICompanyResponse truProxyAPICompanyResponse) {
        Company[] companies = Arrays.stream(truProxyAPICompanyResponse.getItems()).map(
                trueProxyAPICompany -> new Company(
                        trueProxyAPICompany.getCompanyNumber(),
                        trueProxyAPICompany.getCompanyType(),
                        trueProxyAPICompany.getTitle(),
                        trueProxyAPICompany.getCompanyStatus(),
                        trueProxyAPICompany.getDateOfCreation(),
                        new Address(
                                trueProxyAPICompany.getAddress().getLocality(),
                                trueProxyAPICompany.getAddress().getPostalCode(),
                                trueProxyAPICompany.getAddress().getPremises(),
                                trueProxyAPICompany.getAddress().getAddressLine1(),
                                trueProxyAPICompany.getAddress().getCountry()
                        ),
                        new Officer[]{
                                new Officer(
                                        "BOXALL, Sarah Victoria",
                                        "secretary",
                                        "2008-02-11",
                                        new Address(
                                                "5",
                                                "London",
                                                "Cranford Close",
                                                "England",
                                                "SW20 0DP"
                                        )
                                )
                        }
                )).toArray(Company[]::new);

        return new CompanySearchResponse(companies, truProxyAPICompanyResponse.getTotalResults());
    }
}
