package com.springmsa.deathstar.handlers;

import java.time.LocalDate;

public abstract class ConditionsHandler {
    public static boolean isOldEnough(long age) {
        return age >= 18;
    }

    public static boolean hasValidLicense(LocalDate licenseDate, String licenseNumber) {
        return licenseNumber != null && licenseDate != null;
    }

    public static boolean canRentThisVehicle(int horsepower, long age) {
        if (age < 21 && horsepower >= 8) {
            return false;
        }
        return age >= 25 || horsepower <= 13;
    }
}
