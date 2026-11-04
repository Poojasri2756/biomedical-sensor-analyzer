
package com.biomedical.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.biomedical.model.SensorReading;

public class HTMLReportGenerator {

	public void generateReport(List<SensorReading> readings)
	        throws IOException {

	    if (readings == null || readings.isEmpty()) {
	        throw new IllegalArgumentException(
	            "No valid sensor readings available!");
	    }

	    
        double totalHR = 0;
        double totalSpO2 = 0;
        double totalTemp = 0;

        double minHR = Double.MAX_VALUE;
        double maxHR = -Double.MAX_VALUE;
        for (SensorReading r : readings) {

            double hr = r.getHeartRate();

            totalHR += hr;
            totalSpO2 += r.getSpo2();
            totalTemp += r.getTemperature();

            minHR = Math.min(minHR, hr);
            maxHR = Math.max(maxHR, hr);
        }

        int count = readings.size();

        StringBuilder html = new StringBuilder();

        html.append("<html><head><title>Biomedical Report</title>");
        html.append("<style>");
        html.append("body{font-family:Arial;background:#f2f5f9;padding:30px;}");
        html.append(".card{background:white;padding:20px;margin:15px;border-radius:10px;}");
        html.append("h1{color:#174ea6;}");
        html.append("table{width:100%;border-collapse:collapse;background:white;}");
        html.append("td,th{padding:12px;border:1px solid #ddd;text-align:center;}");
        html.append("th{background:#174ea6;color:white;}");
        html.append("</style></head><body>");

        html.append("<h1>Biomedical Sensor Data Quality Report</h1>");

        html.append("<div class='card'>");
        html.append("<h2>Statistical Summary</h2>");
        html.append("<p>Total Valid Readings: ").append(count).append("</p>");
        html.append("<p>Average Heart Rate: ")
            .append(totalHR / count).append(" BPM</p>");
        html.append("<p>Minimum Heart Rate: ").append(minHR).append(" BPM</p>");
        html.append("<p>Maximum Heart Rate: ").append(maxHR).append(" BPM</p>");
        html.append("<p>Average SpO2: ").append(totalSpO2 / count).append("%</p>");
        html.append("<p>Average Temperature: ")
            .append(totalTemp / count).append(" °C</p>");
        html.append("</div>");

        html.append("<h2>Valid Sensor Readings</h2>");
        html.append("<table><tr><th>Patient ID</th>");
        html.append("<th>Heart Rate</th><th>SpO2</th>");
        html.append("<th>Temperature</th></tr>");

        for (SensorReading r : readings) {
            html.append("<tr><td>").append(r.getPatientId()).append("</td>");
            html.append("<td>").append(r.getHeartRate()).append("</td>");
            html.append("<td>").append(r.getSpo2()).append("</td>");
            html.append("<td>").append(r.getTemperature()).append("</td></tr>");
        }

        html.append("</table></body></html>");

        java.io.File reportFolder = new java.io.File("target");
        reportFolder.mkdirs();

        java.io.File reportFile =
                new java.io.File(reportFolder, "Biomedical_Report.html");

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(html.toString());
        }

        System.out.println("HTML Report Generated Successfully!");
        System.out.println("Report Location: " + reportFile.getAbsolutePath());

        System.out.println("HTML Report Generated Successfully!");
        System.out.println("File: Biomedical_Report.html");
    }
}
