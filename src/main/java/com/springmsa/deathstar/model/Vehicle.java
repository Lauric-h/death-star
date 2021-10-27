package com.springmsa.deathstar.model;

import javax.validation.constraints.NotBlank;

public class Vehicle {
    private int id;

    @NotBlank
    private Type type;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

    @NotBlank
    private String licensePlate;

    @NotBlank
    private int bookingPrice;

    @NotBlank
    private int kmPrice;

    @NotBlank
    private int horsepower;

    // Only for type=truck
    private Integer loadingVolume;

    // Only for type=motorbike
    private Integer cylinder;

    /* ----------------
        Constructors
    */
    public Vehicle(){}

    public Vehicle(
            int id,
            Type type,
            String brand,
            String model,
            String color,
            String licensePlate,
            int bookingPrice,
            int kmPrice,
            int horsepower,
            int parameter
    ) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.bookingPrice = bookingPrice;
        this.kmPrice = kmPrice;
        this.horsepower = horsepower;

        if (type == Type.MOTORBIKE) {
            this.cylinder = parameter;
        }
        if (type == Type.TRUCK) {
            this.loadingVolume = parameter;
        }
    }

    // -----------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(int bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public int getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(int kmPrice) {
        this.kmPrice = kmPrice;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getLoadingVolume() {
        return loadingVolume;
    }

    public void setLoadingVolume(Integer loadingVolume) {
        this.loadingVolume = loadingVolume;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", bookingPrice=" + bookingPrice +
                ", kmPrice=" + kmPrice +
                ", horsepower=" + horsepower +
                ", loadingVolume=" + loadingVolume +
                ", cylinder=" + cylinder +
                '}';
    }
}
