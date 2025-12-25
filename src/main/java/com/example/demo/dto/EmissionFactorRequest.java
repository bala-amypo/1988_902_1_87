package com.example.demo.dto;

public class EmissionFactorRequest {
private Double factorValue;
private String unit;

public EmissionFactorRequest() {}

public EmissionFactorRequest(Double factorValue, String unit) {
this.factorValue = factorValue;
this.unit = unit;
}

public Double getFactorValue() {
return factorValue;
}

public void setFactorValue(Double factorValue) {
this.factorValue = factorValue;
}

public String getUnit() {
return unit;
}

public void setUnit(String unit) {
this.unit = unit;
}
}