package kz.aitu.tc.services;

import kz.aitu.tc.models.Performance;
import kz.aitu.tc.repositories.PerformanceRepositoryInterface;
import kz.aitu.tc.services.interfaces.PerformanceServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceService implements PerformanceServiceInterface {
    private final PerformanceRepositoryInterface repo;

    public PerformanceService(PerformanceRepositoryInterface repo) {
        this.repo = repo;
    }

    // Get all performances.
    @Override
    public List<Performance> getAll() {
        return repo.findAll();
    }

    // Get a single performance by ID.
    @Override
    public Performance getById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Create a new performance.
    @Override
    public Performance create(Performance performance) {
        return repo.save(performance);
    }

    // Find performances by title.
    @Override
    public List<Performance> getByTitle(String title) {
        return repo.findByTitle(title);
    }

    // Find performances by date.
    @Override
    public List<Performance> getByDate(LocalDate date) {
        return repo.findByDate(date);
    }

    // Delete a performance by ID.
    @Override
    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else
            return false;
    }

    // Update an existing performance.
    @Override
    public Performance update(int id, Performance performance) {
        // Throws exception if performance not found
        Performance updatedPerformance = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updatedPerformance.setTitle(performance.getTitle());
        updatedPerformance.setDate(performance.getDate());
        updatedPerformance.setTime(performance.getTime());
        updatedPerformance.setDuration(performance.getDuration());
        updatedPerformance.setVenue(performance.getVenue());

        return repo.save(updatedPerformance);
    }
}
