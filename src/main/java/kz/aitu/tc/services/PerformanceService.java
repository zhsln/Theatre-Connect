package kz.aitu.tc.services;

import kz.aitu.tc.models.Performance;
import kz.aitu.tc.repositories.PerformanceRepositoryInterface;
import kz.aitu.tc.services.interfaces.PerformanceServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class PerformanceService implements PerformanceServiceInterface {
    private final PerformanceRepositoryInterface repo;

    public PerformanceService(PerformanceRepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public List<Performance> getAll() {
        return repo.findAll();
    }

    @Override
    public Performance getById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Performance create(Performance performance) {
        return repo.save(performance);
    }

    @Override
    public List<Performance> getByTitle(String title) {
        return repo.findByTitle(title);
    }

    @Override
    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Performance update(int id, Performance performance) {
        Performance updatedPerformance = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updatedPerformance.setTitle(performance.getTitle());
        updatedPerformance.setDate(performance.getDate());
        updatedPerformance.setTime(performance.getTime());
        updatedPerformance.setDuration(performance.getDuration());
        updatedPerformance.setVenue(performance.getVenue());

        return repo.save(updatedPerformance);
    }
}
