package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TruProxyAPIOfficer {
  @JsonProperty("address")
  private TruProxyAPIAddress address;

  @JsonProperty("name")
  private String name;

  @JsonProperty("appointed_on")
  private String appointedOn;

  @JsonProperty("resigned_on")
  private String resignedOn;

  @JsonProperty("officer_role")
  private String officerRole;

  @JsonProperty("links")
  private TruProxyOfficerLinks links;

  @JsonProperty("date_of_birth")
  private TruProxyOfficerDateOfBirth dateOfBirth;

  @JsonProperty("occupation")
  private String occupation;

  @JsonProperty("country_of_residence")
  private String countryOfResidence;

  @JsonProperty("nationality")
  private String nationality;

  public TruProxyAPIAddress getAddress() {
    return address;
  }

  public void setAddress(TruProxyAPIAddress address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAppointedOn() {
    return appointedOn;
  }

  public void setAppointedOn(String appointedOn) {
    this.appointedOn = appointedOn;
  }

  public String getResignedOn() {
    return resignedOn;
  }

  public void setResignedOn(String resignedOn) {
    this.resignedOn = resignedOn;
  }

  public String getOfficerRole() {
    return officerRole;
  }

  public void setOfficerRole(String officerRole) {
    this.officerRole = officerRole;
  }

  public TruProxyOfficerLinks getLinks() {
    return links;
  }

  public void setLinks(TruProxyOfficerLinks links) {
    this.links = links;
  }

  public TruProxyOfficerDateOfBirth getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(TruProxyOfficerDateOfBirth dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getCountryOfResidence() {
    return countryOfResidence;
  }

  public void setCountryOfResidence(String countryOfResidence) {
    this.countryOfResidence = countryOfResidence;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TruProxyAPIOfficer that = (TruProxyAPIOfficer) o;
    return Objects.equals(address, that.address) && Objects.equals(name, that.name) && Objects.equals(appointedOn, that.appointedOn) && Objects.equals(resignedOn, that.resignedOn) && Objects.equals(officerRole, that.officerRole) && Objects.equals(links, that.links) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(occupation, that.occupation) && Objects.equals(countryOfResidence, that.countryOfResidence) && Objects.equals(nationality, that.nationality);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, name, appointedOn, resignedOn, officerRole, links, dateOfBirth, occupation, countryOfResidence, nationality);
  }
}
