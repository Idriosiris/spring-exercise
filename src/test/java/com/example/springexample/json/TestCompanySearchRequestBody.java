package com.example.springexample.json;

import java.util.Objects;

public class TestCompanySearchRequestBody {
    private String companyName;
    private String companyNumber;

    public TestCompanySearchRequestBody(String companyName, String companyNumber) {
        this.companyName = companyName;
        this.companyNumber = companyNumber;
    }

    public TestCompanySearchRequestBody(String companyName) {
        this.companyName = companyName;
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
        TestCompanySearchRequestBody that = (TestCompanySearchRequestBody) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(companyNumber, that.companyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, companyNumber);
    }
}
