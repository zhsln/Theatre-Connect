package kz.aitu.tc.services;

import kz.aitu.tc.models.User;
import kz.aitu.tc.repositories.UserRepositoryInterface;
import kz.aitu.tc.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface repo;

    // Constructor to inject the User repository.
    public UserService(UserRepositoryInterface repo) {
        this.repo = repo;
    }

    // Retrieve all users.
    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    // Get a single user by ID.
    @Override
    public User getById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Create a new user.
    @Override
    public User create(User user) {
        return repo.save(user);
    }

    // Find users by surname.
    @Override
    public List<User> getBySurname(String surname) {
        return repo.findBySurname(surname);
    }

    // Find users by name.
    @Override
    public List<User> getByName(String name) {
        return repo.findByName(name);
    }

    // Get all users with editor role.
    @Override
    public List<User> getEditors() {
        return repo.findByEditorTrue();
    }

    // Get all users with manager role.
    @Override
    public List<User> getManagers() {
        return repo.findByManagerTrue();
    }

    // Delete a user by ID.
    @Override
    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else
            return false;
    }

    // Update an existing user.
    @Override
    public User update(int id, User user) {
        // Throws exception if user not found.
        User updatedUser = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setEditor(user.isEditor());
        updatedUser.setManager(user.isManager());

        return repo.save(updatedUser);
    }
}
