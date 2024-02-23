package kz.aitu.tc.repositories;

import kz.aitu.tc.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepositoryInterface extends JpaRepository<Performance, Integer> {
    List<Performance> findByTitle(String title);
}
