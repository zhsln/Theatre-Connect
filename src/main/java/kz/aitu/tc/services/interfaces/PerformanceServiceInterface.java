package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Performance;

import java.time.LocalDate;
import java.util.List;

public interface PerformanceServiceInterface {
    // Create a new performance
    Performance create(Performance performance);

    // Update an existing performance.
    Performance update(int id, Performance performance);

    // Delete a performance by its ID. Returns true if the deletion was successful, false otherwise.
    boolean deleteById(int id);

    // Retrieve a performance by its ID.
    Performance getById(int id);

    // Get all performances with a specific title.
    List<Performance> getByTitle(String title);

    // Get all performances on a specific date.
    List<Performance> getByDate(LocalDate date);

    // Get all performances.
    List<Performance> getAll();
}
