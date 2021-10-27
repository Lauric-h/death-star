package com.springmsa.deathstar.model;

public enum Type {
    CAR("Voiture"),
    MOTORBIKE("Moto"),
    TRUCK("Utilitaire");

    private final String displayValue;

    private Type(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
