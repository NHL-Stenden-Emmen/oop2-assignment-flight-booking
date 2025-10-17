package com.nhlstenden.flightbooking;

import com.nhlstenden.flightbooking.airplane.CommercialPlane;
import com.nhlstenden.flightbooking.airplane.SeatType;
import com.nhlstenden.flightbooking.airport.AirportCode;
import com.nhlstenden.flightbooking.flight.Flight;
import com.nhlstenden.flightbooking.flight.FlightStatus;
import com.nhlstenden.flightbooking.flight.Person;
import com.nhlstenden.flightbooking.flight.Ticket;
import com.nhlstenden.flightbooking.luggage.Luggage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FlightManagerTest
{
    private FlightManager flightManager;
    private Person person;
    private Set<Luggage> luggage;

    @BeforeEach
    void setup()
    {
        this.flightManager = new FlightManager();
        this.person = new Person("Jan");
        this.luggage = new HashSet<>(); // No luggage
    }

    @Test
    void bookTicket_noFlightsAdded_throwsException()
    {
        // Assert
        assertThrows(FlightNotAvailableException.class, () -> {
            // Act
            this.flightManager.bookTicket(AirportCode.AMS, AirportCode.LAX, this.person, this.luggage);
        });
    }

    @Test
    void bookTicket_amsToLaxAvailable_shouldCreateTicket()
    {
        CommercialPlane commercialPlane = new CommercialPlane("KLM1", 1);
        commercialPlane.addSeatOfType(SeatType.ECONOMY);

        Flight flight = new Flight(AirportCode.AMS, AirportCode.LAX, LocalDateTime.now().plusDays(1), FlightStatus.WAITING, commercialPlane);

        this.flightManager.addFlight(flight);

        Ticket ticket = this.flightManager.bookTicket(AirportCode.AMS, AirportCode.LAX, this.person, this.luggage);
        assertTrue(flight.getTickets().contains(ticket));
    }
}