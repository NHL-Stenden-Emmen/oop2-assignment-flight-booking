package com.nhlstenden.flightbooking.flight;

import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.airplane.AirPlane;
import com.nhlstenden.flightbooking.airport.AirportDistance;
import com.nhlstenden.flightbooking.datasource.FlightDataSource;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Flight implements FlightDataSource
{
    private AirportCode departure;
    private AirportCode arrival;
    private LocalDateTime departureTime;
    private FlightStatus status;
    private AirPlane airPlane;
    private Set<Ticket> tickets;

    public Flight(AirportCode departure, AirportCode arrival, LocalDateTime departureTime, FlightStatus status, AirPlane airPlane)
    {
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.status = status;
        this.airPlane = airPlane;
        this.tickets = new HashSet<>();
    }

    public AirportCode getDeparture()
    {
        return this.departure;
    }

    public void setDeparture(AirportCode departure)
    {
        this.departure = departure;
    }

    public AirportCode getArrival()
    {
        return this.arrival;
    }

    public void setArrival(AirportCode arrival)
    {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureTime()
    {
        return this.departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime)
    {
        this.departureTime = departureTime;
    }

    public FlightStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(FlightStatus status)
    {
        this.status = status;
    }

    public AirPlane getAirPlane()
    {
        return this.airPlane;
    }

    public void setAirPlane(AirPlane airPlane)
    {
        this.airPlane = airPlane;
    }

    public Set<Ticket> getTickets()
    {
        return this.tickets;
    }

    public void setTickets(Set<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket)
    {
        this.tickets.add(ticket);
    }

    public int getDistanceInKm()
    {
        AirportDistance airportDistance = new AirportDistance();

        return airportDistance.getDistance(this.departure, this.arrival);
    }

    public double getLuggageWeight()
    {
        double luggageWeightInKg = 0.0;

        for (Ticket ticket : this.tickets)
        {
            luggageWeightInKg += ticket.getLuggageWeightInKg();
        }

        return luggageWeightInKg;
    }

    public boolean isDoable()
    {
        return this.airPlane.hasEnoughFuel(this);
    }

    public void depart()
    {
        if (this.isDoable())
        {
            this.status = FlightStatus.DEPARTING;
        }
    }

    public boolean hasSeatsLeft()
    {
        int ticketSold = this.tickets.size();
        int numberOfSeatsInAirplane = this.airPlane.getNumberOfSeats();

        return ticketSold < numberOfSeatsInAirplane;
    }

    @Override
    public String getData()
    {
        return "F: " + this.departure + " -> " + this.arrival + ". " + this.departureTime;
    }
}
