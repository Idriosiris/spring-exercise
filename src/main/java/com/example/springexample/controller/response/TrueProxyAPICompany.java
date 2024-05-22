package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrueProxyAPICompany {
    @JsonProperty("company_status")
    private String companyStatus;

    @JsonProperty("address_snippet")
    private String addressSnippet;

    @JsonProperty("date_of_creation")
    private String dateOfCreation;

    @JsonProperty("matches")
    private Matches matches;

    @JsonProperty("description")
    private String description;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("company_number")
    private String companyNumber;

    @JsonProperty("title")
    private String title;

    @JsonProperty("company_type")
    private String companyType;

    @JsonProperty("address")
    private TruProxyAPIAddress address;

    /*
     * TODO: This might be an enum?
     * */
    @JsonProperty("kind")
    private String kind;

    /*
    * TODO: This might be an enum?
    * */
    @JsonProperty("description_identifier")
    private String[] descriptionIdentifier;

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getAddressSnippet() {
        return addressSnippet;
    }

    public void setAddressSnippet(String addressSnippet) {
        this.addressSnippet = addressSnippet;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Matches getMatches() {
        return matches;
    }

    public void setMatches(Matches matches) {
        this.matches = matches;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public TruProxyAPIAddress getAddress() {
        return address;
    }

    public void setAddress(TruProxyAPIAddress address) {
        this.address = address;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String[] getDescriptionIdentifier() {
        return descriptionIdentifier;
    }

    public void setDescriptionIdentifier(String[] descriptionIdentifier) {
        this.descriptionIdentifier = descriptionIdentifier;
    }
}
