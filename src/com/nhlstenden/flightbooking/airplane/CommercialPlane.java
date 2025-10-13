package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommercialPlane extends AirPlane
{
    private Map<SeatType, Integer> seats;

    public CommercialPlane(String code, double fuelLevelInLiters)
    {
        super(code, fuelLevelInLiters);

        this.seats = new HashMap<>();
    }

    public Map<SeatType, Integer> getSeats()
    {
        return this.seats;
    }

    public void setSeats(Map<SeatType, Integer> seats)
    {
        this.seats = seats;
    }

    public void addSeatOfType(SeatType seatType)
    {
        this.seats.putIfAbsent(seatType, 0);
        this.seats.computeIfPresent(seatType, (key, value) -> value + 1);
    }

    private int getEconomySeatsTaken(Flight flight)
    {
        int ticketsBought = flight.getTickets().size();
        int amountOfEconomySeats = this.getSeats().get(SeatType.ECONOMY);

        return Math.min(ticketsBought, amountOfEconomySeats);
    }

    private int getBusinessSeatsTaken(Flight flight)
    {
        int ticketsBought = flight.getTickets().size();
        int amountOfEconomySeats = this.getEconomySeatsTaken(flight);

        if (ticketsBought > amountOfEconomySeats)
        {
            return ticketsBought - amountOfEconomySeats;
        }

        return 0;
    }

    @Override
    public int getNumberOfSeats()
    {
        return this.getSeats().getOrDefault(SeatType.ECONOMY, 0) +
                this.getSeats().getOrDefault(SeatType.BUSINESS, 0);
    }

    @Override
    public double getFuelUsage(Flight flight)
    {
        int amountOfEconomySeats = this.getSeats().get(SeatType.ECONOMY);
        int amountOfBusinessSeats = this.getSeats().get(SeatType.BUSINESS);
        int flightDistance = flight.getDistanceInKm();
        int occupiedEconomySeats = this.getEconomySeatsTaken(flight);
        int occupiedBusinessSeats = this.getBusinessSeatsTaken(flight);
        double luggageWeight = flight.getLuggageWeight();

        return (amountOfEconomySeats * 1.75) + (amountOfBusinessSeats * 1.98) * flightDistance +
                (occupiedEconomySeats * 2.02) + (occupiedBusinessSeats * 2.87) + (luggageWeight * 0.3);
    }
}
