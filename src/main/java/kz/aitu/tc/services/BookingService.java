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
                          PerformanceServiceInterface performanceService)
    {
        this.bookingRepositoryInterface = bookingRepositoryInterface;
        this.userService = userService;
        this.performanceService = performanceService;
    }

    @Override
    public Booking create(Booking booking) {
        // Перед сохранением проверяем, что у booking есть связанные объекты User и Performance
        if (booking.getUser() == null || booking.getPerformance() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User and Performance are required for booking");
        }

        // Пытаемся создать бронирование
        return bookingRepositoryInterface.save(booking);
    }


    @Override
    public Booking update(int id, Booking booking) {
        Booking updatedBooking = bookingRepositoryInterface.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = userService.getById(booking.getUser().getId());
        Performance performance = performanceService.getById(booking.getPerformance().getId());

        if (user == null || performance == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user or performance ID");
        }

        updatedBooking.setUser(user);
        updatedBooking.setPerformance(performance);
        updatedBooking.setSeat_number(booking.getSeat_number());

        return bookingRepositoryInterface.save(updatedBooking);
    }


    @Override
    public boolean deleteById(int id) {
        if (bookingRepositoryInterface.existsById(id)) {
            bookingRepositoryInterface.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepositoryInterface.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getByUserId(int id) {
        return bookingRepositoryInterface.findByUserId(id);
    }

    @Override
    public List<Booking> getByPerformanceId(int id) {
        return bookingRepositoryInterface.findByPerformanceId(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepositoryInterface.findAll();
    }
}
