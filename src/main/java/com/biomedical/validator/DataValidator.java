
package com.biomedical.validator;

import com.biomedical.model.SensorReading;

public class DataValidator {

    public boolean validate(SensorReading reading) {

        boolean valid = true;

        System.out.println("\nPatient ID: " + reading.getPatientId());

        // Heart Rate validation
        if (reading.getHeartRate() < 40 ||
            reading.getHeartRate() > 180) {

            System.out.println("Invalid Heart Rate!");
            valid = false;
        }

        // SpO2 validation
        if (reading.getSpo2() < 70 ||
            reading.getSpo2() > 100) {

            System.out.println("Invalid SpO2!");
            valid = false;
        }

        // Temperature validation
        if (reading.getTemperature() < 30 ||
            reading.getTemperature() > 43) {

            System.out.println("Invalid Temperature!");
            valid = false;
        }

        if (valid) {
            System.out.println("Reading is VALID");
        }

        return valid;
    }
}
