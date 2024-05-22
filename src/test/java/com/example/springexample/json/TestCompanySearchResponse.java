package com.example.springexample.json;

import com.example.springexample.domain.Company;

import java.util.Arrays;
import java.util.Objects;

public class TestCompanySearchResponse {
    private TestCompany[] items;
    private int totalResults;

    public TestCompanySearchResponse(TestCompany[] companies, int totalResults) {
        this.items = companies;
        this.totalResults = totalResults;
    }

    public TestCompany[] getItems() {
        return items;
    }

    public void setItems(TestCompany[] items) {
        this.items = items;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCompanySearchResponse that = (TestCompanySearchResponse) o;
        return totalResults == that.totalResults && Arrays.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(totalResults);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }
}
