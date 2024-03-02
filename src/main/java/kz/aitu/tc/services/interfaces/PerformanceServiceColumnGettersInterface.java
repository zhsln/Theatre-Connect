package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Performance;

import java.time.LocalDate;
import java.util.List;

// Interface to get information from the columns of the same name.
public interface PerformanceServiceColumnGettersInterface {

    // Retrieve a performance by its ID.
    Performance getById(int id);

    // Get all performances with a specific title.
    List<Performance> getByTitle(String title);

    // Get all performances on a specific date.
    List<Performance> getByDate(LocalDate date);

    // Get all performances.
    List<Performance> getAll();
}
