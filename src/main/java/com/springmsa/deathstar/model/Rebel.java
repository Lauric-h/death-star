package com.springmsa.deathstar.model;

import java.util.Date;


public class Rebel {

    private String lastName;
    private String firstName;
    private Date birthDate;
    private String licenseNumber;
    private Date licenseDate;

    public Rebel(String lastName, String firstName, Date birthDate, String licenseNumber, Date licenseDate){

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }
}
