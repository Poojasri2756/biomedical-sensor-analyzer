
package com.biomedical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.biomedical.model.SensorReading;
import com.biomedical.validator.DataValidator;

class AppTest {

    @Test
    void testSensorReadingCreation() {

        SensorReading reading =
                new SensorReading("P001", 75, 98, 36.5);

        assertEquals("P001", reading.getPatientId());
        assertEquals(75.0, reading.getHeartRate());
        assertEquals(98.0, reading.getSpo2());
        assertEquals(36.5, reading.getTemperature());
    }

    @Test
    void testValidSensorReading() {

        SensorReading reading =
                new SensorReading("P002", 80, 97, 37.0);

        DataValidator validator = new DataValidator();

        assertTrue(validator.validate(reading));
    }
}
