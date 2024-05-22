package com.example.springexample.json;

import java.util.Objects;

public class TestAddress {
  private String locality;
  private String postCode;
  private String premises;
  private String addressLine;
  private String country;

  public TestAddress(String locality, String postCode, String premises, String addressLine, String country) {
    this.locality = locality;
    this.postCode = postCode;
    this.premises = premises;
    this.addressLine = addressLine;
    this.country = country;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getPremises() {
    return premises;
  }

  public void setPremises(String premises) {
    this.premises = premises;
  }

  public String getAddressLine() {
    return addressLine;
  }

  public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
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
    TestAddress address = (TestAddress) o;
    return Objects.equals(locality, address.locality) && Objects.equals(postCode, address.postCode) && Objects.equals(premises, address.premises) && Objects.equals(addressLine, address.addressLine) && Objects.equals(country, address.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locality, postCode, premises, addressLine, country);
  }
}
