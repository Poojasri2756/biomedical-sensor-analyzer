
package com.biomedical.model;

public class SensorReading {

    private String patientId;
    private double heartRate;
    private double spo2;
    private double temperature;

    public SensorReading(String patientId, double heartRate,
                         double spo2, double temperature) {

        this.patientId = patientId;
        this.heartRate = heartRate;
        this.spo2 = spo2;
        this.temperature = temperature;
    }

    public String getPatientId() {
        return patientId;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public double getSpo2() {
        return spo2;
    }

    public double getTemperature() {
        return temperature;
    }
}
