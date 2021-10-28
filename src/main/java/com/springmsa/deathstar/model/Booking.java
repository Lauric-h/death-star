package com.springmsa.deathstar.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private int vehicleId;

    @NotBlank
    private int clientId;

    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @NotBlank
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    public Booking(){};

    public Booking(int vehicleId, int clientId, LocalDate startDate, LocalDate endDate) {
        this.vehicleId = vehicleId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", clientId=" + clientId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
