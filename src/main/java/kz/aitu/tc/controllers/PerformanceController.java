package kz.aitu.tc.controllers;

import kz.aitu.tc.models.Performance;
import kz.aitu.tc.services.interfaces.PerformanceServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("performances")
public class PerformanceController {
    private final PerformanceServiceInterface service;

    public PerformanceController(PerformanceServiceInterface service) {
        this.service = service;
    }

    // Get all performances.
    @GetMapping("/")
    public ResponseEntity<List<Performance>> getAllBookings() {
        List<Performance> performances = service.getAll();
        return performances.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(performances, HttpStatus.OK);
    }

    // Get a specific performance by its ID.
    @GetMapping("/{performance_id}")
    public ResponseEntity<Performance> getById(@PathVariable("performance_id") int id) {
        Performance performance = service.getById(id);
        return performance != null ? new ResponseEntity<>(performance, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new performance.
    @PostMapping("/")
    public ResponseEntity<Performance> create(@RequestBody Performance performance){
        Performance createdPerformance = service.create(performance);
        return createdPerformance != null ? new ResponseEntity<>(createdPerformance, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Get all performances by title.
    @GetMapping("/title/{performance_title}")
    public ResponseEntity<List<Performance>> getAllByTitle(@PathVariable("performance_title") String title){
        List<Performance> performances = service.getByTitle(title);
        return performances.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(performances, HttpStatus.OK);
    }

    // Get all performances by date.
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Performance>> getAllByDate(@PathVariable("date") LocalDate date) {
        List<Performance> performances = service.getByDate(date);
        return performances.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(performances, HttpStatus.OK);
    }

    // Delete a performance by its ID.
    @DeleteMapping("/{performance_id}")
    public ResponseEntity<Void> deleteById(@PathVariable("performance_id") int performance_id) {
        boolean deleted = service.deleteById(performance_id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a specific performance by its ID.
    @PutMapping("/update/{performance_id}")
    public ResponseEntity<Performance> update(@PathVariable("performance_id") int performance_id, @RequestBody Performance performance) {
        Performance updatedPerformance = service.update(performance_id, performance);
        return updatedPerformance != null ? new ResponseEntity<>(updatedPerformance, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
