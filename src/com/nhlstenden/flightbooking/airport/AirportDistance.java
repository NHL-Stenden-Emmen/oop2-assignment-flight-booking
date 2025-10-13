package com.nhlstenden.flightbooking.airport;

import java.util.HashMap;
import java.util.Map;

public class AirportDistance
{
    private Map<AirportCode, Map<AirportCode, Integer>> distanceMap;

    public AirportDistance()
    {
        this.distanceMap = new HashMap<>();
        this.fillMap();
    }

    private void fillMap()
    {
        HashMap<AirportCode, Integer> departureJfk = new HashMap<>();
        departureJfk.put(AirportCode.AMS, 5848);
        departureJfk.put(AirportCode.MEX, 3366);
        departureJfk.put(AirportCode.LAX, 3975);

        HashMap<AirportCode, Integer> departureAms = new HashMap<>();
        departureAms.put(AirportCode.MEX, 9206);
        departureAms.put(AirportCode.LAX, 8956);
        departureAms.put(AirportCode.JFK, 5848);

        HashMap<AirportCode, Integer> departureMex = new HashMap<>();
        departureMex.put(AirportCode.LAX, 2500);
        departureMex.put(AirportCode.AMS, 9206);
        departureMex.put(AirportCode.JFK, 3366);

        HashMap<AirportCode, Integer> departureLax = new HashMap<>();
        departureLax.put(AirportCode.JFK, 3975);
        departureLax.put(AirportCode.AMS, 8956);
        departureLax.put(AirportCode.MEX, 2500);

        this.distanceMap.put(AirportCode.JFK, departureJfk);
        this.distanceMap.put(AirportCode.AMS, departureAms);
        this.distanceMap.put(AirportCode.MEX, departureMex);
        this.distanceMap.put(AirportCode.LAX, departureLax);
    }

    public int getDistance(AirportCode departure, AirportCode arrival)
    {
        if (!this.distanceMap.containsKey(arrival) || !this.distanceMap.containsKey(departure))
        {
            throw new IllegalArgumentException("Airport Code does not exist.");
        }

        if (departure == arrival)
        {
            throw new IllegalArgumentException("Airport Codes are the same.");
        }

        Map<AirportCode, Integer> destinations = this.distanceMap.get(departure);
        return destinations.get(arrival);
    }
}
