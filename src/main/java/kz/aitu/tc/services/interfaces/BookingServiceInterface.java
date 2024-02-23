package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Booking;

import java.util.List;

public interface BookingServiceInterface {
    Booking create(Booking booking);
    Booking update(int id, Booking booking);
    boolean deleteById(int id);
    Booking getBookingById(int id);
    List<Booking> getByUserId(int id);
    List<Booking> getByPerformanceId(int id);
    List<Booking> getAll();
}