package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Booking;

import java.util.List;

public interface BookingServiceInterface {
    // Create a new booking
    Booking create(Booking booking);

    // Update an existing booking.
    Booking update(int id, Booking booking);

    // Delete a booking by its ID. Returns true if the deletion was successful, false otherwise.
    boolean deleteById(int id);

    // Retrieve a booking by its ID.
    Booking getBookingById(int id);

    // Get all bookings made by a specific user, identified by the user's ID.
    List<Booking> getByUserId(int id);

    // Get all bookings for a specific performance, identified by the performance's ID.
    List<Booking> getByPerformanceId(int id);

    // Get all bookings.
    List<Booking> getAll();
}
