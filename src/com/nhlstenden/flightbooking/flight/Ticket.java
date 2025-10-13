package com.nhlstenden.flightbooking.flight;

import com.nhlstenden.flightbooking.luggage.Luggage;

import java.util.HashSet;
import java.util.Set;

public class Ticket
{
    private Person person;
    private Set<Luggage> luggage;

    public Ticket(Person person)
    {
        this.person = person;
        this.luggage = new HashSet<>();
    }

    public Person getPerson()
    {
        return this.person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Set<Luggage> getLuggage()
    {
        return this.luggage;
    }

    public void setLuggage(Set<Luggage> luggage)
    {
        this.luggage = luggage;
    }

    public void addLuggage(Luggage luggage)
    {
        this.luggage.add(luggage);
    }

    public double getLuggageWeightInKg()
    {
        double weightInKg = 0;

        for (Luggage luggage : this.luggage)
        {
            weightInKg += luggage.getWeightInKg();
        }

        return weightInKg;
    }
}
