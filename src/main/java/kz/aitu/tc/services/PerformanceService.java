package kz.aitu.tc.services;

import kz.aitu.tc.exceptionHandler.exceptions.AlreadyExistsException;
import kz.aitu.tc.exceptionHandler.exceptions.ResourceNotFoundException;
import kz.aitu.tc.models.Performance;
import kz.aitu.tc.repositories.PerformanceRepositoryInterface;
import kz.aitu.tc.services.interfaces.PerformanceServiceColumnGettersInterface;
import kz.aitu.tc.services.interfaces.PerformanceServiceInterface;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceService implements PerformanceServiceInterface, PerformanceServiceColumnGettersInterface {
    private final PerformanceRepositoryInterface repo;

    public PerformanceService(PerformanceRepositoryInterface repo) {
        this.repo = repo;
    }

    // Get all performances.
    @Override
    public List<Performance> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    // Get a single performance by ID.
    @Override
    public Performance getById(int id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Performance with ID " + id + " not found."));
    }

    // Create a new performance.
    @Override
    public Performance create(Performance performance) {
        List<Performance> existingPerformances = repo.findByTitleAndDateAndTimeAndDurationAndVenue(
                performance.getTitle(),
                performance.getDate(),
                performance.getTime(),
                performance.getDuration(),
                performance.getVenue()
        );

        if (!existingPerformances.isEmpty())
            throw new AlreadyExistsException("Performance already exists.");

        return repo.save(performance);
    }

    // Find performances by title.
    @Override
    public List<Performance> getByTitle(String title) {
        return repo.findAllByTitle(title);
    }

    // Find performances by date.
    @Override
    public List<Performance> getByDate(LocalDate date) {
        return repo.findAllByDate(date);
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
        Performance updatedPerformance = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Performance with ID " + id + " not found."));

        updatedPerformance.setTitle(performance.getTitle());
        updatedPerformance.setDate(performance.getDate());
        updatedPerformance.setTime(performance.getTime());
        updatedPerformance.setDuration(performance.getDuration());
        updatedPerformance.setVenue(performance.getVenue());

        return repo.save(updatedPerformance);
    }
}
