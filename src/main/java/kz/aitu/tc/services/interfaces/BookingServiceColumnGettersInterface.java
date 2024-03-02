package kz.aitu.tc.services.interfaces;


import kz.aitu.tc.models.Booking;

import java.util.List;

// Interface to get information from the columns of the same name.
public interface BookingServiceColumnGettersInterface {
    // Retrieve a booking by its ID.
    Booking getBookingById(int id);

    // Get all bookings made by a specific user, identified by the user's ID.
    List<Booking> getByUserId(int id);

    // Get all bookings for a specific performance, identified by the performance's ID.
    List<Booking> getByPerformanceId(int id);

    // Get all bookings.
    List<Booking> getAll();
}