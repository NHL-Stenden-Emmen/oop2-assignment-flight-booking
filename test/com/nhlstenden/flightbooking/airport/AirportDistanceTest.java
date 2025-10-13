package com.nhlstenden.flightbooking.airport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportDistanceTest
{
    @Test
    void getDistance_jfkToAms_shouldReturn5848()
    {
        AirportDistance airportDistance = new AirportDistance();

        assertEquals(5848, airportDistance.getDistance(AirportCode.JFK, AirportCode.AMS));
    }

    @Test
    void getDistance_amsToJfk_shouldReturn5848()
    {
        AirportDistance airportDistance = new AirportDistance();

        assertEquals(5848, airportDistance.getDistance(AirportCode.AMS, AirportCode.JFK));
    }

    @Test
    void getDistance_nullToAms_shouldThrowException()
    {
        AirportDistance airportDistance = new AirportDistance();

        assertThrows(IllegalArgumentException.class, () -> {
           airportDistance.getDistance(null, AirportCode.AMS);
        });
    }

    @Test
    void getDistance_amsToNull_shouldThrowException()
    {
        AirportDistance airportDistance = new AirportDistance();

        assertThrows(IllegalArgumentException.class, () -> {
            airportDistance.getDistance(AirportCode.AMS, null);
        });
    }

    @Test
    void getDistance_amsToAms_shouldThrowException()
    {
        AirportDistance airportDistance = new AirportDistance();

        assertThrows(IllegalArgumentException.class, () -> {
            airportDistance.getDistance(AirportCode.AMS, AirportCode.AMS);
        });
    }
}