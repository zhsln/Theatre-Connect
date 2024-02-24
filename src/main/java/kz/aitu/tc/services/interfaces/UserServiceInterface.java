package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.User;

import java.util.List;

public interface UserServiceInterface {
    // Get all users
    List<User> getAll();

    // Retrieve a user by their ID
    User getById(int id);

    // Create a new user
    User create(User user);

    // Get all users with a specific surname
    List<User> getBySurname(String surname);

    // Get all users with a specific name
    List<User> getByName(String name);

    // Get all users who are editors
    List<User> getEditors();

    // Get all users who are managers
    List<User> getManagers();

    // Delete a user by their ID. Returns true if the deletion was successful, false otherwise.
    boolean deleteById(int id);

    // Update an existing user.
    User update(int id, User user);
}
