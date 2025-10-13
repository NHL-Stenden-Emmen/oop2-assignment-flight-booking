package com.nhlstenden.flightbooking.luggage;

public class Luggage
{
    private double weightInKg;
    private LuggageType type;

    public Luggage(double weightInKg, LuggageType type)
    {
        this.weightInKg = weightInKg;
        this.type = type;
    }

    public double getWeightInKg()
    {
        return this.weightInKg;
    }

    public void setWeightInKg(double weightInKg)
    {
        this.weightInKg = weightInKg;
    }

    public LuggageType getType()
    {
        return this.type;
    }

    public void setType(LuggageType type)
    {
        this.type = type;
    }
}
