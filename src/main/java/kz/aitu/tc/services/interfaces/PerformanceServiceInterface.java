package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.Performance;

import java.time.LocalDate;
import java.util.List;

public interface PerformanceServiceInterface {
    Performance create(Performance performance);
    Performance update(int id, Performance performance);
    boolean deleteById(int id);
    Performance getById(int id);
    List<Performance> getByTitle(String title);
    List<Performance> getByDate(LocalDate date);
    List<Performance> getAll();
}