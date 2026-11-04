
package com.biomedical.reader;

import com.biomedical.model.SensorReading;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public List<SensorReading> readSensorData() {

        List<SensorReading> readings = new ArrayList<>();

        try {
            InputStream input = getClass()
                    .getResourceAsStream("/sample_data.csv");

            if (input == null) {
                System.out.println("CSV file not found!");
                return readings;
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(input));

            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {

                lineNumber++;

                if (lineNumber == 1) {
                    continue;
                }

                String[] data = line.split(",", -1);

                try {
                    if (data.length != 4 ||
                        data[0].trim().isEmpty() ||
                        data[1].trim().isEmpty() ||
                        data[2].trim().isEmpty() ||
                        data[3].trim().isEmpty()) {

                        System.out.println(
                            "Missing data at row: " + lineNumber);
                        continue;
                    }

                    String patientId = data[0].trim();
                    double heartRate = Double.parseDouble(data[1].trim());
                    double spo2 = Double.parseDouble(data[2].trim());
                    double temperature = Double.parseDouble(data[3].trim());

                    readings.add(new SensorReading(
                        patientId, heartRate, spo2, temperature));

                } catch (NumberFormatException e) {
                    System.out.println(
                        "Corrupted data at row: " + lineNumber);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        return readings;
    }
}
