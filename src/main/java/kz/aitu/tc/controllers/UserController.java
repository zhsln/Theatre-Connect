package kz.aitu.tc.controllers;

import jakarta.validation.Valid;
import kz.aitu.tc.models.User;
import kz.aitu.tc.services.interfaces.UserServiceColumnGettersInterface;
import kz.aitu.tc.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserServiceInterface service;
    private final UserServiceColumnGettersInterface serviceGetter;

    public UserController(UserServiceInterface service, UserServiceColumnGettersInterface serviceGetter) {
        this.service = service;
        this.serviceGetter = serviceGetter;
    }

    // Get all users from the database.
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = serviceGetter.getAll();
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get a single user by their ID.
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") int id) {
        User user = serviceGetter.getById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Create a new user.
    @PostMapping("/")
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        User createdUser = service.create(user);
        return createdUser != null ? new ResponseEntity<>(createdUser, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Get all users with a specific surname.
    @GetMapping("/surname/{user_surname}")
    public ResponseEntity<List<User>> getAllBySurname(@PathVariable("user_surname") String surname){
        List<User> users = serviceGetter.getBySurname(surname);
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get all users with a specific name.
    @GetMapping("/name/{user_name}")
    public ResponseEntity<List<User>> getAllByName(@PathVariable("user_name") String name) {
        List<User> users = serviceGetter.getByName(name);
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get all users who are editors.
    @GetMapping("/editors/")
    public ResponseEntity<List<User>> getAllEditors() {
        List<User> editors = serviceGetter.getEditors();
        return editors.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(editors, HttpStatus.OK);
    }

    // Get all users who are managers.
    @GetMapping("/managers/")
    public ResponseEntity<List<User>> getAllManagers() {
        List<User> managers = serviceGetter.getManagers();
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
    public ResponseEntity<User> update(@PathVariable("user_id") int user_id, @Valid @RequestBody User user) {
        User updatedUser = service.update(user_id, user);
        return updatedUser != null ? new ResponseEntity<>(updatedUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}