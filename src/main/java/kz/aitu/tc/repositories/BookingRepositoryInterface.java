package kz.aitu.tc.repositories;

import kz.aitu.tc.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepositoryInterface extends JpaRepository<Booking, Integer> {
    // Find bookings made by a specific user. Returns a list of bookings linked to the user's ID.
    List<Booking> findByUserId(int user_id);

    // Find bookings for a specific performance. Returns a list of bookings linked to the performance's ID.
    List<Booking> findByPerformanceId(int performance_id);

    // Find bookings with specific user_id, performance_id and seatNumber.
    // This method is used to check is booking already exists or not.
    List<Booking> findByUserIdAndPerformanceIdAndSeatNumber(int id, int id1, String seatNumber);
}