package com.example.companylookup.lookup.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Entity
public class CompanySearchEntity {
  @Id
  private String id;

  @JdbcTypeCode(SqlTypes.JSON)
  private String response;

  public CompanySearchEntity() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CompanySearchEntity that = (CompanySearchEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(response, that.response);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, response);
  }

  @Override
  public String toString() {
    return "CompanySearchEntity{" +
            "id='" + id + '\'' +
            ", response='" + response + '\'' +
            '}';
  }
}
