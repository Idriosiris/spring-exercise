package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TruProxyOfficerDateOfBirth {
    @JsonProperty("month")
    private int month;

    @JsonProperty("year")
    private int year;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruProxyOfficerDateOfBirth that = (TruProxyOfficerDateOfBirth) o;
        return month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year);
    }
}
