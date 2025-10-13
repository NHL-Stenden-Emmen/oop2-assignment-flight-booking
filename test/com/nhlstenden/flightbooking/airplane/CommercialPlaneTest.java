package com.nhlstenden.flightbooking.airplane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommercialPlaneTest
{
    private CommercialPlane commercialPlane;

    @BeforeEach
    void setup()
    {
        this.commercialPlane = new CommercialPlane("KLM1", 1);
    }

    @Test
    void constructor_noSeats_noSeats()
    {
        assertNull(this.commercialPlane.getSeats().get(SeatType.ECONOMY));
    }

    @Test
    void addSeatOfType_addOneEconomySeatInEmptyPlane_shouldHaveOneEconomySeat()
    {
        this.commercialPlane.addSeatOfType(SeatType.ECONOMY);

        assertEquals(1, this.commercialPlane.getSeats().get(SeatType.ECONOMY));
    }
}