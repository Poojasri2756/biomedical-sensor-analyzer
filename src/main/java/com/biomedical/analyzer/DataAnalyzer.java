
package com.biomedical.analyzer;

import java.util.List;
import com.biomedical.model.SensorReading;

public class DataAnalyzer {

    public void analyze(List<SensorReading> readings) {

        if (readings == null || readings.isEmpty()) {
            System.out.println("No valid readings available!");
            return;
        }

        double totalHeartRate = 0;
        double totalSpo2 = 0;
        double totalTemperature = 0;

        double minHeartRate = Double.MAX_VALUE;
        double maxHeartRate = Double.MIN_VALUE;

        for (SensorReading reading : readings) {

            double hr = reading.getHeartRate();

            totalHeartRate += hr;
            totalSpo2 += reading.getSpo2();
            totalTemperature += reading.getTemperature();

            if (hr < minHeartRate) {
                minHeartRate = hr;
            }

            if (hr > maxHeartRate) {
                maxHeartRate = hr;
            }
        }

        int count = readings.size();

        System.out.println("\n===== SENSOR STATISTICS =====");

        System.out.println("Total Valid Readings: " + count);

        System.out.println("Average Heart Rate: "
                + totalHeartRate / count);

        System.out.println("Minimum Heart Rate: " + minHeartRate);

        System.out.println("Maximum Heart Rate: " + maxHeartRate);

        System.out.println("Average SpO2: "
                + totalSpo2 / count);

        System.out.println("Average Temperature: "
                + totalTemperature / count);
    }
}
