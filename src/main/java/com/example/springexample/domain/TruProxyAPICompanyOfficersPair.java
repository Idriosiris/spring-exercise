package com.example.springexample.domain;

import com.example.springexample.controller.response.TruProxyAPICompany;
import com.example.springexample.controller.response.TruProxyAPIOfficer;

public class TruProxyAPICompanyOfficersPair {
  private TruProxyAPICompany company;
  private TruProxyAPIOfficer[] officers;

  public TruProxyAPICompanyOfficersPair(TruProxyAPICompany truProxyAPICompany, TruProxyAPIOfficer[] officers) {
    this.company = truProxyAPICompany;
    this.officers = officers;
  }

  public TruProxyAPICompany getCompany() {
    return company;
  }

  public void setCompany(TruProxyAPICompany company) {
    this.company = company;
  }

  public TruProxyAPIOfficer[] getOfficers() {
    return officers;
  }

  public void setOfficers(TruProxyAPIOfficer[] officers) {
    this.officers = officers;
  }
}
