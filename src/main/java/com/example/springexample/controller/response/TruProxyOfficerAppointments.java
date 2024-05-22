package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TruProxyOfficerAppointments {
    @JsonProperty("appointments")
    private String appointments;

    public String getAppointments() {
        return appointments;
    }

    public void setAppointments(String appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruProxyOfficerAppointments that = (TruProxyOfficerAppointments) o;
        return Objects.equals(appointments, that.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointments);
    }
}
