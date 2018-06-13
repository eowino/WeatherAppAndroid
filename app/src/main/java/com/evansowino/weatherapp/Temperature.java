package com.evansowino.weatherapp;

public class Temperature {
    private String type;
    private String value;
    private String unit;

    public Temperature(String type, String value, String unit) {
        this.type = type;
        this.value = value;
        this.unit = unit;
    }

    public Temperature() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
