package com.springmsa.deathstar.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Rebel {

    private String lastName;
    private String firstName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;
    private String licenseNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate licenseDate;

    public Rebel(String lastName, String firstName, LocalDate birthDate, String licenseNumber, LocalDate licenseDate){

        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.licenseNumber = licenseNumber;
        this.licenseDate =  licenseDate;

    }

    public Rebel(){

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

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
}
