package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;

import java.util.Set;

public class PrivatePlane extends AirPlane
{
    private int numberOfSeats;

    public PrivatePlane(String code, double fuelLevelInLiters, int numberOfSeats)
    {
        super(code, fuelLevelInLiters);

        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public int getNumberOfSeats()
    {
        return this.numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats)
    {
        this.numberOfSeats = numberOfSeats;
    }

    public void addSeat()
    {
        this.numberOfSeats++;
    }

    @Override
    public double getFuelUsage(Flight flight)
    {
        int amountOfSeats = this.getNumberOfSeats();
        int flightDistance = flight.getDistanceInKm();
        int amountOfOccupiedSeats = flight.getTickets().size();
        double luggageWeight = flight.getLuggageWeight();

        return amountOfSeats * 1.31 * flightDistance + (amountOfOccupiedSeats * 1.87) + (luggageWeight * 0.4);
    }

    @Override
    public boolean isLuggageAllowed(Set<Luggage> luggage)
    {
        for (Luggage pieceOfLuggage : luggage)
        {
            if (pieceOfLuggage.getType() == LuggageType.CHECKED)
            {
                return false;
            }
        }

        return super.isLuggageAllowed(luggage);
    }
}
