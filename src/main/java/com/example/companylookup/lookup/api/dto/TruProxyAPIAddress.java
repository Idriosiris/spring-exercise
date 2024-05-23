package com.example.companylookup.lookup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TruProxyAPIAddress {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TruProxyAPIAddress that = (TruProxyAPIAddress) o;
    return Objects.equals(premises, that.premises) && Objects.equals(postalCode, that.postalCode) && Objects.equals(country, that.country) && Objects.equals(locality, that.locality) && Objects.equals(addressLine1, that.addressLine1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(premises, postalCode, country, locality, addressLine1);
  }
}
