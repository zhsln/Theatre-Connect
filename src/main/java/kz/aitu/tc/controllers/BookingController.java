package kz.aitu.tc.controllers;

import jakarta.validation.Valid;
import kz.aitu.tc.models.Booking;
import kz.aitu.tc.models.Performance;
import kz.aitu.tc.models.User;
import kz.aitu.tc.services.interfaces.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingController {
    private final BookingServiceInterface bookingService;
    private final BookingServiceColumnGettersInterface bookingServiceGetter;
    private final UserServiceColumnGettersInterface userServiceGetter; // Injected to manage users.
    private final PerformanceServiceColumnGettersInterface performanceServiceGetter; // Injected to manage performances.

    public BookingController(BookingServiceInterface bookingService, BookingServiceColumnGettersInterface bookingServiceGetter,
                             UserServiceColumnGettersInterface userServiceGetter,
                             PerformanceServiceColumnGettersInterface performanceServiceGetter) {
        this.bookingService = bookingService;
        this.bookingServiceGetter = bookingServiceGetter;
        this.userServiceGetter = userServiceGetter;
        this.performanceServiceGetter = performanceServiceGetter;
    }

    // Create a new booking.
    @PostMapping("/")
    public ResponseEntity<Booking> create(@Valid @RequestBody Booking booking) {
        User user = userServiceGetter.getById(booking.getUser().getId());
        Performance performance = performanceServiceGetter.getById(booking.getPerformance().getId());

        if (user == null || performance == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // If user or performance is not found

        booking.setUser(user);
        booking.setPerformance(performance);

        Booking createdBooking = bookingService.create(booking);
        return createdBooking != null ? new ResponseEntity<>(createdBooking, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Update an existing booking by ID.
    @PutMapping("/update/{booking_id}")
    public ResponseEntity<Booking> update(@PathVariable("booking_id") int booking_id, @Valid @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.update(booking_id, booking);
        return updatedBooking != null ? new ResponseEntity<>(updatedBooking, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Delete a booking by its ID.
    @DeleteMapping("/{booking_id}")
    public ResponseEntity<Void> deleteById(@PathVariable("booking_id") int booking_id) {
        boolean deleted = bookingService.deleteById(booking_id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get a specific booking by its ID.
    @GetMapping("/{booking_id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("booking_id") int booking_id) {
        Booking booking = bookingServiceGetter.getBookingById(booking_id);
        return booking != null ? new ResponseEntity<>(booking, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all bookings made by a specific user.
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable("user_id") int user_id) {
        List<Booking> bookings = bookingServiceGetter.getByUserId(user_id);
        return bookings.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get all bookings for a specific performance.
    @GetMapping("/performance/{performance_id}")
    public ResponseEntity<List<Booking>> getBookingsByPerformanceId(@PathVariable("performance_id") int performance_id) {
        List<Booking> bookings = bookingServiceGetter.getByPerformanceId(performance_id);
        return bookings.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get all bookings.
    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAll() {
        List<Booking> bookings = bookingServiceGetter.getAll();
        return bookings.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}