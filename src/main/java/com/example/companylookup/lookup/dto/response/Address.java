package com.example.companylookup.lookup.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Address {
  @JsonProperty("locality")
  private String locality;

  @JsonProperty("postal_code")
  private String postalCode;

  @JsonProperty("premises")
  private String premises;

  @JsonProperty("address_line_1")
  private String addressLine1;

  @JsonProperty("country")
  private String country;

  public Address(String locality, String postCode, String premises, String addressLine, String country) {
    this.locality = locality;
    this.postalCode = postCode;
    this.premises = premises;
    this.addressLine1 = addressLine;
    this.country = country;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getPremises() {
    return premises;
  }

  public void setPremises(String premises) {
    this.premises = premises;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(locality, address.locality) && Objects.equals(postalCode, address.postalCode) && Objects.equals(premises, address.premises) && Objects.equals(addressLine1, address.addressLine1) && Objects.equals(country, address.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locality, postalCode, premises, addressLine1, country);
  }
}
