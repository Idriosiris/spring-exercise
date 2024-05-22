package com.example.springexample.controller.response;

import com.example.springexample.domain.TruProxyAPICompanyOfficersPair;

public class TruProxyAPICompanyOfficersPairs {
  private TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs;
  private int totalResults;

  public TruProxyAPICompanyOfficersPairs(TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs, int totalResults) {
    this.truProxyAPICompanyOfficersPairs = truProxyAPICompanyOfficersPairs;
    this.totalResults = totalResults;
  }

  public TruProxyAPICompanyOfficersPair[] getTruProxyAPICompanyOfficersPairs() {
    return truProxyAPICompanyOfficersPairs;
  }

  public void setTruProxyAPICompanyOfficersPairs(TruProxyAPICompanyOfficersPair[] truProxyAPICompanyOfficersPairs) {
    this.truProxyAPICompanyOfficersPairs = truProxyAPICompanyOfficersPairs;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }
}
