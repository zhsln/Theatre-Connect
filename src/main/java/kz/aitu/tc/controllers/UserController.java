package kz.aitu.tc.controllers;

import kz.aitu.tc.models.User;
import kz.aitu.tc.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserServiceInterface service;

    public UserController(UserServiceInterface service) {
        this.service = service;
    }

    // Get all users from the database.
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAll();
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get a single user by their ID.
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") int id) {
        User user = service.getById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create new user.
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user){
        User createdUser = service.create(user);
        return createdUser != null ? new ResponseEntity<>(createdUser, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Get all users with a specific surname.
    @GetMapping("/surname/{user_surname}")
    public ResponseEntity<List<User>> getAllBySurname(@PathVariable("user_surname") String surname){
        List<User> users = service.getBySurname(surname);
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get all users with a specific name.
    @GetMapping("/name/{user_name}")
    public ResponseEntity<List<User>> getAllByName(@PathVariable("user_name") String name) {
        List<User> users = service.getByName(name);
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get all users who are editors.
    @GetMapping("/editors/")
    public ResponseEntity<List<User>> getAllEditors() {
        List<User> editors = service.getEditors();
        return editors.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(editors, HttpStatus.OK);
    }

    // Get all users who are managers.
    @GetMapping("/managers/")
    public ResponseEntity<List<User>> getAllManagers() {
        List<User> managers = service.getManagers();
        return managers.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(managers, HttpStatus.OK);
    }

    // Delete a user by their ID.
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteById(@PathVariable("user_id") int user_id) {
        boolean deleted = service.deleteById(user_id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update an existing user's details.
    @PutMapping("/update/{user_id}")
    public ResponseEntity<User> update(@PathVariable("user_id") int user_id, @RequestBody User user) {
        User updatedUser = service.update(user_id, user);
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
