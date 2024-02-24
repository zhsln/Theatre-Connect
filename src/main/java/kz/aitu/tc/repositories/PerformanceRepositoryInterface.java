package kz.aitu.tc.repositories;

import kz.aitu.tc.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerformanceRepositoryInterface extends JpaRepository<Performance, Integer> {
    // Find performances by their title. Returns a list of performances with the matching title.
    List<Performance> findByTitle(String title);

    // Find performances by their date. Returns a list of performances scheduled for the given date.
    List<Performance> findByDate(LocalDate date);
}