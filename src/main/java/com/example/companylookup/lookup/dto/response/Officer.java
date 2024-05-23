package com.example.companylookup.lookup.dto.response;

import java.util.Objects;

public class Officer {
  private String name;
  private String officerRole;
  private String appointedOn;
  private Address address;

  public Officer(String name, String officerRole, String appointedOn, Address address) {
    this.name = name;
    this.officerRole = officerRole;
    this.appointedOn = appointedOn;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOfficerRole() {
    return officerRole;
  }

  public void setOfficerRole(String officerRole) {
    this.officerRole = officerRole;
  }

  public String getAppointedOn() {
    return appointedOn;
  }

  public void setAppointedOn(String appointedOn) {
    this.appointedOn = appointedOn;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Officer officer = (Officer) o;
    return Objects.equals(name, officer.name) && Objects.equals(officerRole, officer.officerRole) && Objects.equals(appointedOn, officer.appointedOn) && Objects.equals(address, officer.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, officerRole, appointedOn, address);
  }
}
