package com.example.companylookup.json;

import java.util.Objects;

public class TestOfficer {
  private String name;
  private String officerRole;
  private String appointedOn;
  private TestAddress address;

  public TestOfficer(String name, String officerRole, String appointedOn, TestAddress address) {
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

  public TestAddress getAddress() {
    return address;
  }

  public void setAddress(TestAddress address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestOfficer officer = (TestOfficer) o;
    return Objects.equals(name, officer.name) && Objects.equals(officerRole, officer.officerRole) && Objects.equals(appointedOn, officer.appointedOn) && Objects.equals(address, officer.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, officerRole, appointedOn, address);
  }
}
