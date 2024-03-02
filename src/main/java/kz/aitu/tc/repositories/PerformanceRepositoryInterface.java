package kz.aitu.tc.repositories;

import kz.aitu.tc.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.List;

@Repository
public interface PerformanceRepositoryInterface extends JpaRepository<Performance, Integer> {
    // Find performances by their title. Returns a list of performances with the matching title.
    List<Performance> findAllByTitle(String title);

    // Find performances by their date. Returns a list of performances scheduled for the given date.
    List<Performance> findAllByDate(LocalDate date);

    // This method is used to check is performance already exists or not.
    List<Performance> findByTitleAndDateAndTimeAndDurationAndVenue(String title,
                                                                   LocalDate date,
                                                                   OffsetTime time,
                                                                   int duration,
                                                                   String venue);
}