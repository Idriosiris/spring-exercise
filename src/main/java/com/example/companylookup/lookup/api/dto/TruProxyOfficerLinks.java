package com.example.companylookup.lookup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TruProxyOfficerLinks {
  @JsonProperty("officer")
  private TruProxyOfficerAppointments truProxyOfficerAppointments;

  public TruProxyOfficerAppointments getTruProxyOfficerAppointments() {
    return truProxyOfficerAppointments;
  }

  public void setTruProxyOfficerAppointments(TruProxyOfficerAppointments truProxyOfficerAppointments) {
    this.truProxyOfficerAppointments = truProxyOfficerAppointments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TruProxyOfficerLinks that = (TruProxyOfficerLinks) o;
    return Objects.equals(truProxyOfficerAppointments, that.truProxyOfficerAppointments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(truProxyOfficerAppointments);
  }
}
