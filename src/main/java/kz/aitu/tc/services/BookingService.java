package kz.aitu.tc.services;

import kz.aitu.tc.exceptionHandler.exceptions.AlreadyExistsException;
import kz.aitu.tc.exceptionHandler.exceptions.ResourceNotFoundException;
import kz.aitu.tc.models.Booking;
import kz.aitu.tc.models.Performance;
import kz.aitu.tc.models.User;
import kz.aitu.tc.repositories.BookingRepositoryInterface;
import kz.aitu.tc.services.interfaces.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService implements BookingServiceInterface, BookingServiceColumnGettersInterface {
    private final BookingRepositoryInterface bookingRepositoryInterface;
    private final UserServiceColumnGettersInterface userServiceGetter;
    private final PerformanceServiceColumnGettersInterface performanceServiceGetter;

    public BookingService(BookingRepositoryInterface bookingRepositoryInterface,
                          UserServiceColumnGettersInterface userServiceGetter,
                          PerformanceServiceColumnGettersInterface performanceServiceGetter) {
        this.bookingRepositoryInterface = bookingRepositoryInterface;
        this.userServiceGetter = userServiceGetter;
        this.performanceServiceGetter = performanceServiceGetter;
    }

    // Create booking.
    @Override
    public Booking create(Booking booking) {
        // Check if User and Performance are linked before creating a booking.
        if (booking.getUser() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User are required for booking");
        if (booking.getPerformance() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Performance are required for booking");

        List<Booking> existingBookings = bookingRepositoryInterface.findByUserIdAndPerformanceIdAndSeatNumber(
                booking.getUser().getId(),
                booking.getPerformance().getId(),
                booking.getSeatNumber()
        );

        if (!existingBookings.isEmpty())
            throw new AlreadyExistsException("This booking already exists.");

        return bookingRepositoryInterface.save(booking);
    }

    // Update information about booking.
    @Override
    public Booking update(int id, Booking booking) {
        // Check if the booking exists before updating.
        Booking updatedBooking = bookingRepositoryInterface.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking with ID " + id + " not found."));

        // Check is valid User and Performance IDs are provided.
        User user = userServiceGetter.getById(booking.getUser().getId());
        Performance performance = performanceServiceGetter.getById(booking.getPerformance().getId());

        if (user == null || performance == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user or performance ID");

        updatedBooking.setUser(user);
        updatedBooking.setPerformance(performance);
        updatedBooking.setSeatNumber(booking.getSeatNumber());

        return bookingRepositoryInterface.save(updatedBooking);
    }

    // Delete the booking if it exists.
    @Override
    public boolean deleteById(int id) {
        if (bookingRepositoryInterface.existsById(id)) {
            bookingRepositoryInterface.deleteById(id);
            return true;
        } else
            return false;
    }

    // Get a booking by its ID.
    @Override
    public Booking getBookingById(int id) {
        return bookingRepositoryInterface.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking with ID " + id + " not found."));
    }

    // Get all bookings made by a specific user.
    @Override
    public List<Booking> getByUserId(int id) {
        return bookingRepositoryInterface.findByUserId(id);
    }

    // Get all bookings for a specific performance.
    @Override
    public List<Booking> getByPerformanceId(int id) {
        return bookingRepositoryInterface.findByPerformanceId(id);
    }

    // Get all bookings.
    @Override
    public List<Booking> getAll() {
        return bookingRepositoryInterface.findAll();
    }
}