package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Performance;

// Interface of basic operations with database.
public interface PerformanceServiceInterface {
    // Create a new performance
    Performance create(Performance performance);

    // Update an existing performance.
    Performance update(int id, Performance performance);

    // Delete a performance by its ID. Returns true if the deletion was successful, false otherwise.
    boolean deleteById(int id);
}
