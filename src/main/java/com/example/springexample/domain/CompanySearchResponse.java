package com.example.springexample.domain;

import java.util.Arrays;
import java.util.Objects;

public class CompanySearchResponse {
  private Company[] items;
  private int totalResults;

  public CompanySearchResponse(Company[] companies, int totalResults) {
    this.items = companies;
    this.totalResults = totalResults;
  }

  public Company[] getItems() {
    return items;
  }

  public void setItems(Company[] items) {
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
    CompanySearchResponse that = (CompanySearchResponse) o;
    return totalResults == that.totalResults && Arrays.equals(items, that.items);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(totalResults);
    result = 31 * result + Arrays.hashCode(items);
    return result;
  }
}
