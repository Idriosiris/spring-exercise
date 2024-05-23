package com.example.companylookup.lookup.dto.request;

import java.util.Objects;

public class PostCompaniesSearchRequest {
  private String companyName;
  private String companyNumber;

  public PostCompaniesSearchRequest(String companyName, String companyNumber) {
    this.companyName = companyName;
    this.companyNumber = companyNumber;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyNumber() {
    return companyNumber;
  }

  public void setCompanyNumber(String companyNumber) {
    this.companyNumber = companyNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostCompaniesSearchRequest that = (PostCompaniesSearchRequest) o;
    return Objects.equals(companyName, that.companyName) && Objects.equals(companyNumber, that.companyNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyName, companyNumber);
  }
}
