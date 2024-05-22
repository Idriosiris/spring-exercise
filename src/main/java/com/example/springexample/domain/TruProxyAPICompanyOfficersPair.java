package com.example.springexample.domain;

import com.example.springexample.controller.response.TruProxyAPIOfficer;
import com.example.springexample.controller.response.TrueProxyAPICompany;

public class TruProxyAPICompanyOfficersPair {
    private TrueProxyAPICompany company;
    private TruProxyAPIOfficer[] officers;

    public TruProxyAPICompanyOfficersPair(TrueProxyAPICompany trueProxyAPICompany, TruProxyAPIOfficer[] officers) {
        this.company = trueProxyAPICompany;
        this.officers = officers;
    }

    public TrueProxyAPICompany getCompany() {
        return company;
    }

    public void setCompany(TrueProxyAPICompany company) {
        this.company = company;
    }

    public TruProxyAPIOfficer[] getOfficers() {
        return officers;
    }

    public void setOfficers(TruProxyAPIOfficer[] officers) {
        this.officers = officers;
    }
}
