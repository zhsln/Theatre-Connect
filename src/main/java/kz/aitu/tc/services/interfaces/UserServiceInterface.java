package kz.aitu.tc.services.interfaces;

import kz.aitu.tc.models.User;

// Interface of basic operations with database.
public interface UserServiceInterface {
    // Create a new user
    User create(User user);

    // Delete a user by their ID. Returns true if the deletion was successful, false otherwise.
    boolean deleteById(int id);

    // Update an existing user.
    User update(int id, User user);
}