package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.datasource.FlightDataSource;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;

import java.util.Set;

public abstract class AirPlane implements FlightDataSource
{
    private String code;
    private double fuelLevelInLiters;

    public AirPlane(String code, double fuelLevelInLiters)
    {
        this.code = code;
        this.fuelLevelInLiters = fuelLevelInLiters;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public double getFuelLevelInLiters()
    {
        return this.fuelLevelInLiters;
    }

    public void setFuelLevelInLiters(double fuelLevelInLiters)
    {
        this.fuelLevelInLiters = fuelLevelInLiters;
    }

    public abstract double getFuelUsage(Flight flight);

    public boolean hasEnoughFuel(Flight flight)
    {
        return this.getFuelLevelInLiters() > this.getFuelUsage(flight);
    }

    public boolean isLuggageAllowed(Set<Luggage> luggage)
    {
        int amountOfHandLuggage = 0;

        for (Luggage pieceOfLuggage : luggage)
        {
            if (pieceOfLuggage.getType() == LuggageType.HAND)
            {
                amountOfHandLuggage++;
            }
        }

        return amountOfHandLuggage <= 1;
    }

    public abstract int getNumberOfSeats();

    @Override
    public String getData()
    {
        return "P: " + this.code + ". Brandstof: " + this.fuelLevelInLiters;
    }
}
