package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrueProxyAPICompanyResponse {
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
}
