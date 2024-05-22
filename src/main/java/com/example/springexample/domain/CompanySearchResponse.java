package com.example.springexample.domain;

import java.util.Arrays;

public class CompanySearchResponse {
    private Company[] companies;

    public CompanySearchResponse(Company[] companies) {
        this.companies = companies;
    }

    public Company[] getCompanies() {
        return companies;
    }

    public void setCompanies(Company[] companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySearchResponse that = (CompanySearchResponse) o;
        return Arrays.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(companies);
    }
}
