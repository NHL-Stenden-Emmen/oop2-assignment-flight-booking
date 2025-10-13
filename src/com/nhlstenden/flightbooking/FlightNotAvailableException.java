package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airport.AirportCode;

public class FlightNotAvailableException extends RuntimeException
{
    public FlightNotAvailableException()
    {
        super("Ja, gewoon geen vlucht beschikbaar.");
    }

    public FlightNotAvailableException(AirportCode arrival, AirportCode destination)
    {
        super("There is no flight available between " + arrival + " and " + destination);
    }
}
