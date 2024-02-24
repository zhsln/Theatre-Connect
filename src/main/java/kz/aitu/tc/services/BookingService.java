package kz.aitu.tc.services;

import kz.aitu.tc.models.Booking;
import kz.aitu.tc.models.Performance;
import kz.aitu.tc.models.User;
import kz.aitu.tc.repositories.BookingRepositoryInterface;
import kz.aitu.tc.services.interfaces.BookingServiceInterface;
import kz.aitu.tc.services.interfaces.PerformanceServiceInterface;
import kz.aitu.tc.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService implements BookingServiceInterface {
    private final BookingRepositoryInterface bookingRepositoryInterface;
    private final UserServiceInterface userService;
    private final PerformanceServiceInterface performanceService;

    public BookingService(BookingRepositoryInterface bookingRepositoryInterface,
                          UserServiceInterface userService,
                          PerformanceServiceInterface performanceService) {
        this.bookingRepositoryInterface = bookingRepositoryInterface;
        this.userService = userService;
        this.performanceService = performanceService;
    }

    // Create booking.
    @Override
    public Booking create(Booking booking) {
        // Check if User and Performance are linked before creating a booking.
        if (booking.getUser() == null || booking.getPerformance() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User and Performance are required for booking");
        }

        return bookingRepositoryInterface.save(booking);
    }

    // Update information about booking.
    @Override
    public Booking update(int id, Booking booking) {
        // Check if the booking exists before updating.
        Booking updatedBooking = bookingRepositoryInterface.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Check is valid User and Performance IDs are provided.
        User user = userService.getById(booking.getUser().getId());
        Performance performance = performanceService.getById(booking.getPerformance().getId());

        if (user == null || performance == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user or performance ID");

        updatedBooking.setUser(user);
        updatedBooking.setPerformance(performance);
        updatedBooking.setSeat_number(booking.getSeat_number());

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
        return bookingRepositoryInterface.findById(id).orElse(null);
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
