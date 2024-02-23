package kz.aitu.tc.repositories;

import kz.aitu.tc.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepositoryInterface extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(int user_id);
    List<Booking> findByPerformanceId(int performance_id);
}
