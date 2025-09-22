package main.java.com.ccp.core.dto;

public record VitalDTO(String patientId, int heartRate, double sleepHours, double weightChange, long tsMillis) {}
