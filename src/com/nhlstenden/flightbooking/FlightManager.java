package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airplane.CommercialPlane;
import com.nhlstenden.flightbooking.airplane.PrivatePlane;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.flight.FlightStatus;
import com.nhlstenden.flightbooking.flight.Person;
import com.nhlstenden.flightbooking.flight.Ticket;
import com.nhlstenden.flightbooking.luggage.Luggage;
import com.nhlstenden.flightbooking.luggage.LuggageType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FlightManager
{
    private Set<Flight> flights;

    public FlightManager()
    {
        this.flights = new HashSet<>();
    }

    public Set<Flight> getFlights()
    {
        return this.flights;
    }

    public void setFlights(Set<Flight> flights)
    {
        this.flights = flights;
    }

    public void addFlight(Flight flight)
    {
        this.flights.add(flight);
    }

    public Ticket bookTicket(AirportCode departure, AirportCode destination, Person person, Set<Luggage> luggage)
    {
        Flight availableFlight = this.getAvailableFlight(departure, destination);
        if (availableFlight == null)
        {
            throw new FlightNotAvailableException(departure, destination);
        }

        this.validateFlight(luggage, availableFlight);

        return this.createTicket(availableFlight, person, luggage);
    }

    private void validateFlight(Set<Luggage> luggage, Flight availableFlight)
    {
        if (!availableFlight.getAirPlane().isLuggageAllowed(luggage))
        {
            throw new RuntimeException("Invalid luggage.");
        }

        if (!availableFlight.hasSeatsLeft())
        {
            throw new RuntimeException("This flight is fully booked.");
        }
    }

    private Ticket createTicket(Flight flight, Person person, Set<Luggage> luggage)
    {
        Ticket ticket = new Ticket(person);
        flight.getTickets().add(ticket);

        ticket.setLuggage(luggage);

        return ticket;
    }

    private Flight getAvailableFlight(AirportCode departure, AirportCode destination)
    {
        Flight availableFlight = null;

        for (Flight flight : this.flights)
        {
            if (flight.getDeparture() == departure && flight.getArrival() == destination && flight.getDepartureTime().isAfter(LocalDateTime.now()) && flight.getStatus() == FlightStatus.WAITING)
            {
                availableFlight = flight;
            }
        }

        return availableFlight;
    }
}
