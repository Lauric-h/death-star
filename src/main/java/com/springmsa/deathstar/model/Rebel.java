package com.springmsa.deathstar.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Rebel {

    @Id
    @GeneratedValue
    private int id;
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

    public Rebel(){}

    @Override
    public String toString() {
        return "Rebel{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licenseDate=" + licenseDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
