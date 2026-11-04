
package com.biomedical;

import java.util.ArrayList;
import java.util.List;

import com.biomedical.model.SensorReading;
import com.biomedical.reader.CSVReader;
import com.biomedical.validator.DataValidator;
import com.biomedical.analyzer.DataAnalyzer;
import com.biomedical.report.HTMLReportGenerator;

public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("BIOMEDICAL SENSOR DATA ANALYZER");
        System.out.println("================================");

        try {

            // Step 1: Read sensor data from CSV
            System.out.println("\nStep 1: Reading Sensor Dataset...");

            CSVReader csvReader = new CSVReader();

            List<SensorReading> readings =
                    csvReader.readSensorData();

            System.out.println("Total Records Read: " + readings.size());


            // Step 2: Validate sensor readings
            System.out.println("\nStep 2: Validating Sensor Data...");

            DataValidator validator = new DataValidator();

            List<SensorReading> validReadings =
                    new ArrayList<>();

            for (SensorReading reading : readings) {

                if (validator.validate(reading)) {
                    validReadings.add(reading);
                }
            }

            System.out.println("Valid Records: " + validReadings.size());
            System.out.println("Invalid Records: "
                    + (readings.size() - validReadings.size()));


            // Step 3: Analyze valid sensor data
            System.out.println("\nStep 3: Analyzing Sensor Data...");

            DataAnalyzer analyzer = new DataAnalyzer();

            analyzer.analyze(validReadings);


            // Step 4: Generate HTML report
            System.out.println("\nStep 4: Generating HTML Report...");

            HTMLReportGenerator reportGenerator =
                    new HTMLReportGenerator();

            reportGenerator.generateReport(validReadings);


            // Final result
            System.out.println("\n================================");
            System.out.println("BIOMEDICAL ANALYSIS COMPLETED");
            System.out.println("================================");

            System.out.println("Total Records: " + readings.size());
            System.out.println("Valid Records: " + validReadings.size());
            System.out.println("Invalid Records: "
                    + (readings.size() - validReadings.size()));

            System.out.println("HTML Report: Biomedical_Report.html");

            System.out.println("\nApplication Executed Successfully!");

        } catch (Exception e) {

            System.out.println("\nERROR: Application Execution Failed!");
            System.out.println("Reason: " + e.getMessage());

            e.printStackTrace();
        }
    }
}
