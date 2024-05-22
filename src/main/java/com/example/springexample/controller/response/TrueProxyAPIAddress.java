package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrueProxyAPIAddress {
    @JsonProperty("premises")
    private String premises;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("address_line_1")
    private String addressLine1;

    public String getPremises() {
        return premises;
    }

    public void setPremises(String premises) {
        this.premises = premises;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
}
