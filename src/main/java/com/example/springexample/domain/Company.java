package com.example.springexample.domain;

import java.util.Arrays;
import java.util.Objects;

public class Company {
  private String companyNumber;
  private String companyType;
  private String title;
  private String companyStatus;
  private String dateOfCreation;
  private Address address;
  private Officer[] officers;

  public Company(String companyNumber, String companyType, String title, String companyStatus, String dateOfCreation, Address address, Officer[] officers) {
    this.companyNumber = companyNumber;
    this.companyType = companyType;
    this.title = title;
    this.companyStatus = companyStatus;
    this.dateOfCreation = dateOfCreation;
    this.address = address;
    this.officers = officers;
  }

  public String getCompanyNumber() {
    return companyNumber;
  }

  public void setCompanyNumber(String companyNumber) {
    this.companyNumber = companyNumber;
  }

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCompanyStatus() {
    return companyStatus;
  }

  public void setCompanyStatus(String companyStatus) {
    this.companyStatus = companyStatus;
  }

  public String getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(String dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Officer[] getOfficers() {
    return officers;
  }

  public void setOfficers(Officer[] officers) {
    this.officers = officers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Company company = (Company) o;
    return Objects.equals(companyNumber, company.companyNumber) && Objects.equals(companyType, company.companyType) && Objects.equals(title, company.title) && Objects.equals(companyStatus, company.companyStatus) && Objects.equals(dateOfCreation, company.dateOfCreation) && Objects.equals(address, company.address) && Arrays.equals(officers, company.officers);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(companyNumber, companyType, title, companyStatus, dateOfCreation, address);
    result = 31 * result + Arrays.hashCode(officers);
    return result;
  }
}
