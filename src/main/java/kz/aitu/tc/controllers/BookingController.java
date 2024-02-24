package kz.aitu.tc.controllers;

import kz.aitu.tc.models.Booking;
import kz.aitu.tc.models.Performance;
import kz.aitu.tc.models.User;
import kz.aitu.tc.services.interfaces.BookingServiceInterface;
import kz.aitu.tc.services.interfaces.PerformanceServiceInterface;
import kz.aitu.tc.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingController {
    private final BookingServiceInterface bookingService;
    private final UserServiceInterface userService; // Injected to manage users.
    private final PerformanceServiceInterface performanceService; // Injected to manage performances.

    public BookingController(BookingServiceInterface bookingService,
                             UserServiceInterface userService,
                             PerformanceServiceInterface performanceService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.performanceService = performanceService;
    }

    // Create a new booking.
    @PostMapping("/")
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        User user = userService.getById(booking.getUser().getId());
        Performance performance = performanceService.getById(booking.getPerformance().getId());

        if (user == null || performance == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // If user or performance is not found

        booking.setUser(user);
        booking.setPerformance(performance);

        Booking createdBooking = bookingService.create(booking);
        return createdBooking != null ? new ResponseEntity<>(createdBooking, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Update an existing booking by ID.
    @PutMapping("/update/{booking_id}")
    public ResponseEntity<Booking> update(@PathVariable("booking_id") int booking_id, @RequestBody Booking booking) {
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
        Booking booking = bookingService.getBookingById(booking_id);
        return booking != null ? new ResponseEntity<>(booking, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all bookings made by a specific user.
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable("user_id") int user_id) {
        List<Booking> bookings = bookingService.getByUserId(user_id);
        return bookings.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get all bookings for a specific performance.
    @GetMapping("/performance/{performance_id}")
    public ResponseEntity<List<Booking>> getBookingsByPerformanceId(@PathVariable("performance_id") int performance_id) {
        List<Booking> bookings = bookingService.getByPerformanceId(performance_id);
        return bookings.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get all bookings.
    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAll() {
        List<Booking> bookings = bookingService.getAll();
        return bookings.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}