package com.springmsa.deathstar.model;

import com.springmsa.deathstar.handlers.ConditionsHandler;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class VehicleBooking {
    Vehicle vehicle;
    int estimatedKm;
    LocalDate startDate;
    LocalDate endDate;
    double totalPrice;
    long totalDays;
    LocalDate birthDate;
    long clientAge;
    String licenseNumber;
    LocalDate licenseDate;

    // Constructors
    public VehicleBooking() {};

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(LocalDate licenseDate) {
        this.licenseDate = licenseDate;
    }

    public VehicleBooking(
            Vehicle vehicle,
            int estimatedKm,
            LocalDate startDate,
            LocalDate endDate,
            LocalDate birthDate,
            String licenseNumber,
            LocalDate licenseDate) {
        this.vehicle = vehicle;
        this.estimatedKm = estimatedKm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.birthDate = birthDate;
        this.setClientAge();
        this.setTotalDays();
        this.setTotalPrice();
        this.licenseDate = licenseDate;
        this.licenseNumber = licenseNumber;
    };

    // Getters and setters
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getEstimatedKm() {
        return estimatedKm;
    }

    public void setEstimatedKm(int estimatedKm) {
        this.estimatedKm = estimatedKm;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    // Calculates price depending on vehicleType and estimatedKm
    public void setTotalPrice() {
        if (Objects.equals(vehicle.getType().getDisplayValue(), "Moto")) {
            this.totalPrice = vehicle.getBookingPrice() + vehicle.getCylinder() * 0.001 * vehicle.getKmPrice() * estimatedKm;
        }
        if (Objects.equals(vehicle.getType().getDisplayValue(), "Utilitaire")) {
            this.totalPrice = vehicle.getBookingPrice() + vehicle.getLoadingVolume() * 0.05 * vehicle.getKmPrice() * estimatedKm;
        }
        this.totalPrice = vehicle.getBookingPrice() + (long) vehicle.getKmPrice() * estimatedKm;
    }

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays() {
        this.totalDays = ChronoUnit.DAYS.between(startDate, endDate);;
    }

    public long getClientAge() {
        return clientAge;
    }

    public void setClientAge() {
        this.clientAge = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean canRentThisVehicle() {
        return ConditionsHandler.hasValidLicense(licenseDate, licenseNumber) &&
                ConditionsHandler.isOldEnough(clientAge) &&
                ConditionsHandler.canRentThisVehicle(vehicle.getHorsepower(), clientAge);
    }

}
