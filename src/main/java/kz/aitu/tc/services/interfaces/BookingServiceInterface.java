package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Booking;

// Interface of basic operations with database.
public interface BookingServiceInterface {
    // Create a new booking
    Booking create(Booking booking);

    // Update an existing booking.
    Booking update(int id, Booking booking);

    // Delete a booking by its ID. Returns true if the deletion was successful, false otherwise.
    boolean deleteById(int id);
}